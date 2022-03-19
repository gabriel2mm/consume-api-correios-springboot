package br.com.estudos.correios.services;

import br.com.estudos.correios.domain.dtos.ZipcodeSearchHistoryDTO;
import br.com.estudos.correios.domain.entity.RecentZipCodeSearch;
import br.com.estudos.correios.domain.dtos.SearchZipcodeDTO;
import br.com.estudos.correios.domain.exception.ObjectNotFound;
import br.com.estudos.correios.domain.mappers.RecentZipCodeSearchMapper;
import br.com.estudos.correios.repository.RecentZipCodeSearchRepository;
import br.com.estudos.correios.webServices.SearchZipCodeClient;
import br.com.webservices.correios.stub.ConsultaCEPResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ZipCodeSearchService {

    private final SearchZipCodeClient searchZipCodeClient;
    private final RecentZipCodeSearchMapper recentZipCodeSearchMapper;
    private final RecentZipCodeSearchRepository recentZipCodeSearchRepository;

    public SearchZipcodeDTO searchZipCode(final String CEP){
        ConsultaCEPResponse response = Optional.ofNullable(searchZipCodeClient.getCEP(CEP))
                .orElseThrow(() -> new ObjectNotFound("Zipcode not found."));

        SearchZipcodeDTO searchCEPDTO = new SearchZipcodeDTO(
                response.getReturn().getCep(),
                response.getReturn().getUf(),
                response.getReturn().getBairro(),
                response.getReturn().getCidade(),
                response.getReturn().getEnd());

        createRecentSearchCEP(searchCEPDTO.getCEP());

        return searchCEPDTO;
    }

    public void createRecentSearchCEP(final String zipCode){
        RecentZipCodeSearch recentZipCodeSearch = recentZipCodeSearchRepository.findByZipCode(zipCode)
                        .orElse(new RecentZipCodeSearch(null, zipCode, LocalDateTime.now(), 1));

        if(recentZipCodeSearch != null && recentZipCodeSearch.getId() != null){
            recentZipCodeSearch.setNumberQueries(recentZipCodeSearch.getNumberQueries() + 1);
            recentZipCodeSearch.setUpdated(LocalDateTime.now());
        }

        recentZipCodeSearchRepository.saveAndFlush(recentZipCodeSearch);
    }

    public List<ZipcodeSearchHistoryDTO> getRecentZipCodeSearches(){
        return recentZipCodeSearchMapper.map(recentZipCodeSearchRepository.findAllZipCode());
    }

}
