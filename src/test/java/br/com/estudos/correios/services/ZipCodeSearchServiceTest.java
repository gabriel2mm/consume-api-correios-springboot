package br.com.estudos.correios.services;

import br.com.estudos.correios.domain.entity.RecentZipCodeSearch;
import br.com.estudos.correios.domain.exception.ObjectNotFound;
import br.com.estudos.correios.domain.models.ZipCodeSearchResponseDTO;
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

@RunWith(SpringRunner.class)
class ZipCodeSearchServiceTest {

    private static final String CEP = "81230170";
    private RecentZipCodeSearch recentZipCodeSearch;
    private ConsultaCEPResponse consultaCEPResponse;
    private List<RecentZipCodeSearch> recentZipCodeSearches;

    @InjectMocks
    private ZipCodeSearchService zipCodeSearchService;

    @Mock
    private SearchZipCodeClient searchZipCodeClient;

    @Mock
    private RecentZipCodeSearchRepository recentZipCodeSearchRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startRecentZipCodeSearch();
        startConsultaCEPResponse();
        startRecentZipCodeSearches();

    }

    @Test
    void whenExistsZipCodeRecenteSearchThenSumNumberOfQueries() {
        Mockito.when(searchZipCodeClient.getCEP(Mockito.anyString())).thenReturn(this.consultaCEPResponse);
        Mockito.when(recentZipCodeSearchRepository.findByZipCode(Mockito.anyString())).thenReturn(this.recentZipCodeSearch);
        Mockito.when(recentZipCodeSearchRepository.saveAndFlush(Mockito.any())).thenReturn(null);

        ZipCodeSearchResponseDTO zipCodeSearchResponseDTO = zipCodeSearchService.searchZipCode(CEP);

        Assertions.assertNotNull(zipCodeSearchResponseDTO);
        Assertions.assertEquals(CEP, zipCodeSearchResponseDTO.getCEP());
        Assertions.assertEquals("Curitiba", zipCodeSearchResponseDTO.getCidade());
    }

    @Test
    void whenWhitouthZipCodeRecenteSearchThenCreateNewRecentSearch(){
        Mockito.when(searchZipCodeClient.getCEP(Mockito.anyString())).thenReturn(this.consultaCEPResponse);
        Mockito.when(recentZipCodeSearchRepository.findByZipCode(Mockito.anyString())).thenReturn(null);
        Mockito.when(recentZipCodeSearchRepository.saveAndFlush(Mockito.any())).thenReturn(null);

        ZipCodeSearchResponseDTO zipCodeSearchResponseDTO = zipCodeSearchService.searchZipCode(CEP);

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
        Assertions.assertEquals("CEP inexistente!", exception.getMessage());
    }

    @Test
    void getRecentZipCodeSearches() {
        Mockito.when(recentZipCodeSearchRepository.findAllZipCode()).thenReturn(this.recentZipCodeSearches);
        List<RecentZipCodeSearch> recentZipCodeSearches = zipCodeSearchService.getRecentZipCodeSearches();

        Assertions.assertNotNull(recentZipCodeSearches);
        Assertions.assertEquals(1, recentZipCodeSearches.size());
    }

    private void startRecentZipCodeSearch(){
        this.recentZipCodeSearch = new RecentZipCodeSearch();
        this.recentZipCodeSearch.setId(1L);
        this.recentZipCodeSearch.setZipCode(CEP);
        this.recentZipCodeSearch.setNumberQueries(2);
        this.recentZipCodeSearch.setUpdated(LocalDateTime.now());
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
        this.recentZipCodeSearches = new ArrayList<>(){{
            add(recentZipCodeSearch);
        }};
    }
}