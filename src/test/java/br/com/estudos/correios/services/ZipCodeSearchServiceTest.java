package br.com.estudos.correios.services;

import br.com.estudos.correios.domain.dtos.ZipcodeSearchHistoryDTO;
import br.com.estudos.correios.domain.entity.RecentZipCodeSearch;
import br.com.estudos.correios.domain.exception.ObjectNotFound;
import br.com.estudos.correios.domain.dtos.SearchZipcodeDTO;
import br.com.estudos.correios.domain.mappers.RecentZipCodeSearchMapper;
import br.com.estudos.correios.repository.RecentZipCodeSearchRepository;
import br.com.estudos.correios.webServices.SearchZipCodeClient;
import br.com.webservices.correios.stub.ConsultaCEPResponse;
import br.com.webservices.correios.stub.EnderecoERP;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
class ZipCodeSearchServiceTest {

    private static final String CEP = "81230170";
    private RecentZipCodeSearch recentZipCodeSearch;
    private ConsultaCEPResponse consultaCEPResponse;
    private List<RecentZipCodeSearch> recentZipCodeSearches;
    private List<ZipcodeSearchHistoryDTO> zipcodeSearchHistoryDTOS;

    @InjectMocks
    private ZipCodeSearchService zipCodeSearchService;

    @Mock
    private SearchZipCodeClient searchZipCodeClient;

    @Mock
    private RecentZipCodeSearchRepository recentZipCodeSearchRepository;

    @Mock
    private RecentZipCodeSearchMapper recentZipCodeSearchMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startRecentZipCodeSearch();
        startConsultaCEPResponse();
        startRecentZipCodeSearches();
    }

    @Test
    void whenExistsZipCodeRecentSearchThenSumNumberOfQueries() {
        Mockito.when(searchZipCodeClient.getCEP(Mockito.anyString())).thenReturn(this.consultaCEPResponse);
        Mockito.when(recentZipCodeSearchRepository.findByZipCode(Mockito.anyString())).thenReturn(Optional.of(this.recentZipCodeSearch));
        Mockito.when(recentZipCodeSearchRepository.saveAndFlush(Mockito.any())).thenReturn(null);

        SearchZipcodeDTO zipCodeSearchResponseDTO = zipCodeSearchService.searchZipCode(CEP);

        Assertions.assertNotNull(zipCodeSearchResponseDTO);
        Assertions.assertEquals(CEP, zipCodeSearchResponseDTO.getCEP());
        Assertions.assertEquals("Curitiba", zipCodeSearchResponseDTO.getCidade());
    }

    @Test
    void whenWhitouthZipCodeRecenteSearchThenCreateNewRecentSearch(){
        Mockito.when(searchZipCodeClient.getCEP(Mockito.anyString())).thenReturn(this.consultaCEPResponse);
        Mockito.when(recentZipCodeSearchRepository.findByZipCode(Mockito.anyString())).thenReturn(Optional.empty());
        Mockito.when(recentZipCodeSearchRepository.saveAndFlush(Mockito.any())).thenReturn(null);

        SearchZipcodeDTO zipCodeSearchResponseDTO = zipCodeSearchService.searchZipCode(CEP);

        Assertions.assertNotNull(zipCodeSearchResponseDTO);
        Assertions.assertEquals(CEP, zipCodeSearchResponseDTO.getCEP());
        Assertions.assertEquals("Curitiba", zipCodeSearchResponseDTO.getCidade());
    }

    @Test
    void whenZipCodeNullThenReturnException(){
        Mockito.when(searchZipCodeClient.getCEP(Mockito.anyString())).thenReturn(null);
        Mockito.when(recentZipCodeSearchRepository.findByZipCode(Mockito.anyString())).thenReturn(null);
        Mockito.when(recentZipCodeSearchRepository.saveAndFlush(Mockito.any())).thenReturn(null);

        Throwable exception = Assertions.assertThrows(ObjectNotFound.class, () -> zipCodeSearchService.searchZipCode(CEP));
        Assertions.assertEquals("Zipcode not found.", exception.getMessage());
    }

    @Test
    void whenRequestAllZipcodesThenReturnRecentSearchesList() {
        Mockito.when(recentZipCodeSearchRepository.findAllZipCode()).thenReturn(this.recentZipCodeSearches);
        Mockito.when(recentZipCodeSearchMapper.map(Mockito.any())).thenReturn(this.zipcodeSearchHistoryDTOS);

        List<ZipcodeSearchHistoryDTO> recentZipCodeSearches = zipCodeSearchService.getRecentZipCodeSearches();

        Assertions.assertNotNull(recentZipCodeSearches);
        Assertions.assertEquals(1, recentZipCodeSearches.size());
    }

    private void startRecentZipCodeSearch(){
        recentZipCodeSearch = new RecentZipCodeSearch();
        recentZipCodeSearch.setId(1L);
        recentZipCodeSearch.setZipCode(CEP);
        recentZipCodeSearch.setNumberQueries(2);
        recentZipCodeSearch.setUpdated(LocalDateTime.now());
    }

    private void startConsultaCEPResponse(){
        this.consultaCEPResponse = new ConsultaCEPResponse();
        this.consultaCEPResponse.setReturn(new EnderecoERP());
        this.consultaCEPResponse.getReturn().setCep(CEP);
        this.consultaCEPResponse.getReturn().setUf("PR");
        this.consultaCEPResponse.getReturn().setCidade("Curitiba");
        this.consultaCEPResponse.getReturn().setBairro("Campo Comprido");
        this.consultaCEPResponse.getReturn().setEnd("Rua Renato Polatti");
    }

    private void startRecentZipCodeSearches(){
        recentZipCodeSearches = new ArrayList<>(){{
            add(recentZipCodeSearch);
        }};

        zipcodeSearchHistoryDTOS = new ArrayList<>(){{
            add(new ZipcodeSearchHistoryDTO(null, CEP, LocalDateTime.now(), 1));
        }};
    }
}