package br.com.estudos.correios.services;

import br.com.estudos.correios.domain.entity.RecentZipCodeSearch;
import br.com.estudos.correios.domain.exception.ObjectNotFound;
import br.com.estudos.correios.domain.models.ZipCodeSearchResponseDTO;
import br.com.estudos.correios.repository.RecentZipCodeSearchRepository;
import br.com.estudos.correios.webServices.SearchZipCodeClient;
import br.com.webservices.correios.stub.ConsultaCEPResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ZipCodeSearchService {

    private final SearchZipCodeClient searchZipCodeClient;
    private final RecentZipCodeSearchRepository recentZipCodeSearchRepository;

    public ZipCodeSearchService(SearchZipCodeClient searchZipCodeClient,
                                RecentZipCodeSearchRepository recentZipCodeSearchRepository) {
        this.searchZipCodeClient = searchZipCodeClient;
        this.recentZipCodeSearchRepository = recentZipCodeSearchRepository;
    }

    public ZipCodeSearchResponseDTO searchZipCode(final String CEP){
        ConsultaCEPResponse response = searchZipCodeClient.getCEP(CEP);

        if(response == null || response.getReturn() == null){
            throw new ObjectNotFound("CEP inexistente!");
        }

        ZipCodeSearchResponseDTO searchCEPDTO = new ZipCodeSearchResponseDTO(
                response.getReturn().getCep(),
                response.getReturn().getUf(),
                response.getReturn().getBairro(),
                response.getReturn().getCidade(),
                response.getReturn().getEnd());

        createRecentSearchCEP(searchCEPDTO.getCEP());

        return searchCEPDTO;
    }

    public void createRecentSearchCEP(final String zipCode){
        RecentZipCodeSearch recentZipCodeSearch = recentZipCodeSearchRepository.findByZipCode(zipCode);

        if(recentZipCodeSearch == null){
            recentZipCodeSearch = new RecentZipCodeSearch();
            recentZipCodeSearch.setZipCode(zipCode);
            recentZipCodeSearch.setNumberQueries(1);
            recentZipCodeSearch.setUpdated(LocalDateTime.now());
        }else{
            recentZipCodeSearch.setNumberQueries(recentZipCodeSearch.getNumberQueries() + 1);
            recentZipCodeSearch.setUpdated(LocalDateTime.now());
        }
        recentZipCodeSearchRepository.saveAndFlush(recentZipCodeSearch);
    }

    public List<RecentZipCodeSearch> getRecentZipCodeSearches(){
        return this.recentZipCodeSearchRepository.findAllZipCode();
    }

}
