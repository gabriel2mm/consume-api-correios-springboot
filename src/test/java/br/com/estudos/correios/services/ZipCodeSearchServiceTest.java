package br.com.estudos.correios.services;


import br.com.estudos.correios.domain.entity.RecentZipCodeSearch;
import br.com.estudos.correios.domain.exception.ObjectNotFound;
import br.com.estudos.correios.domain.models.ZipCodeSearchResponseDTO;
import br.com.estudos.correios.repository.RecentZipCodeSearchRepository;
import br.com.estudos.correios.webServices.SearchZipCodeClient;
import br.com.webservices.correios.stub.ConsultaCEPResponse;
import br.com.webservices.correios.stub.EnderecoERP;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
class ZipCodeSearchServiceTest {

    private static final String CEP = "81230170";
    private RecentZipCodeSearch recentZipCodeSearch;
    @Mock
    private SearchZipCodeClient searchZipCodeClient;
    @Mock
    private RecentZipCodeSearchRepository recentZipCodeSearchRepository;

    private ConsultaCEPResponse consultaCEPResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.recentZipCodeSearch = new RecentZipCodeSearch();
        this.recentZipCodeSearch.setId(1L);
        this.recentZipCodeSearch.setZipCode(CEP);
        this.recentZipCodeSearch.setNumberQueries(1);
        this.recentZipCodeSearch.setUpdated(LocalDateTime.now());

        consultaCEPResponse = new ConsultaCEPResponse();
        consultaCEPResponse.setReturn(new EnderecoERP());
        consultaCEPResponse.getReturn().setCep(CEP);
        consultaCEPResponse.getReturn().setUf("PR");
        consultaCEPResponse.getReturn().setCidade("Curitiba");
        consultaCEPResponse.getReturn().setBairro("Campo Comprido");
        consultaCEPResponse.getReturn().setEnd("Rua Renato Polatti");
    }

    @Test
    void whenSearchZipCodeThenReturnZipCodeResponseDTO() {
        Mockito.when(searchZipCodeClient.getCEP(CEP)).thenReturn(consultaCEPResponse);

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

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getReturn());
        Assertions.assertNotNull(searchCEPDTO);
        Assertions.assertEquals(CEP, response.getReturn().getCep());
    }

    @Test
    void whenFindZipCodeNewRecentZipCodeSaveIntoDataBaseMock() {
        Mockito.when(recentZipCodeSearchRepository.findByZipCode(CEP)).thenReturn(null);
        Mockito.when(recentZipCodeSearchRepository.saveAndFlush(Mockito.any())).thenReturn(this.recentZipCodeSearch);

        RecentZipCodeSearch recentZipCodeSearchresult = recentZipCodeSearchRepository.findByZipCode(CEP);
        Assertions.assertNull(recentZipCodeSearchresult);

        recentZipCodeSearchresult = new RecentZipCodeSearch();
        recentZipCodeSearchresult.setZipCode(CEP);
        recentZipCodeSearchresult.setNumberQueries(1);
        recentZipCodeSearchresult.setUpdated(LocalDateTime.now());

        recentZipCodeSearchRepository.saveAndFlush(recentZipCodeSearchresult);

        Assertions.assertNotNull(recentZipCodeSearch);
        Assertions.assertEquals(1, recentZipCodeSearch.getId());
        Assertions.assertEquals(CEP, recentZipCodeSearch.getZipCode());
        Assertions.assertEquals(1, recentZipCodeSearch.getNumberQueries());
    }

    @Test
    void whenFindZipCodeUpdatedRecentZipCodeSaveIntoDataBaseMock() {
        Mockito.when(recentZipCodeSearchRepository.findByZipCode(CEP)).thenReturn(this.recentZipCodeSearch);
        Mockito.when(recentZipCodeSearchRepository.saveAndFlush(Mockito.any())).thenReturn(this.recentZipCodeSearch);

        LocalDateTime datetime = LocalDateTime.now();

        RecentZipCodeSearch recentZipCodeSearchresult = recentZipCodeSearchRepository.findByZipCode(CEP);
        recentZipCodeSearchresult.setNumberQueries(recentZipCodeSearchresult.getNumberQueries() + 1);
        recentZipCodeSearchresult.setUpdated(datetime);
        recentZipCodeSearchRepository.saveAndFlush(recentZipCodeSearchresult);

        Assertions.assertNotNull(recentZipCodeSearchresult);
        Assertions.assertEquals(1L , recentZipCodeSearchresult.getId());
        Assertions.assertEquals(datetime, this.recentZipCodeSearch.getUpdated());
    }

    @Test
    void getRecentZipCodeSearches() {
        Mockito.when(this.recentZipCodeSearchRepository.findAllZipCode())
                .thenReturn(new ArrayList() {{
                    add(recentZipCodeSearch);
        }});

        List<RecentZipCodeSearch> recentZipCodeSearches = this.recentZipCodeSearchRepository.findAllZipCode();
        Assertions.assertNotNull(recentZipCodeSearches);
        Assertions.assertEquals(1 , recentZipCodeSearches.size());
        Assertions.assertNotNull(recentZipCodeSearches.get(0));
    }
}