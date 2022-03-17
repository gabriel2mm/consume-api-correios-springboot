package br.com.estudos.correios.controllers;

import br.com.estudos.correios.domain.entity.RecentZipCodeSearch;
import br.com.estudos.correios.domain.mappers.RecentZipCodeSearchMapper;
import br.com.estudos.correios.domain.dtos.ZipcodeSearchHistoryDTO;
import br.com.estudos.correios.domain.dtos.ZipcodeDTO;
import br.com.estudos.correios.domain.dtos.SearchZipcodeDTO;
import br.com.estudos.correios.services.ZipCodeSearchService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
class ZipCodeControllerTest {

    @InjectMocks
    private ZipCodeController zipCodeController;

    @Mock
    private ZipCodeSearchService zipCodeSearchService;

    @Mock
    private RecentZipCodeSearchMapper recentZipCodeSearchMapper;

    private SearchZipcodeDTO zipCodeSearchResponseDTO;

    private List<ZipcodeSearchHistoryDTO> recentZipCodeSearches;

    private static final String CEP = "81230170";

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        startZipCodeSearchResponseDTO();
        startListRecentZipCodeSearch();
    }

    @Test
    void whenSearchByZipcodeThenReturnZipcodeInformationSuccess() {
        Mockito.when(zipCodeSearchService.searchZipCode(Mockito.anyString())).thenReturn(this.zipCodeSearchResponseDTO);
        ResponseEntity<SearchZipcodeDTO> responseDTOResponseEntity = zipCodeController.searchByZipCode(new ZipcodeDTO(CEP));

        Assertions.assertNotNull(responseDTOResponseEntity);
        Assertions.assertNotNull(responseDTOResponseEntity.getBody());
        Assertions.assertNotNull(responseDTOResponseEntity.getStatusCode());
        Assertions.assertEquals(HttpStatus.OK, responseDTOResponseEntity.getStatusCode());
        Assertions.assertEquals(ResponseEntity.class, responseDTOResponseEntity.getClass());
        Assertions.assertEquals(SearchZipcodeDTO.class, responseDTOResponseEntity.getBody().getClass());

        Assertions.assertEquals(CEP, responseDTOResponseEntity.getBody().getCEP());
        Assertions.assertEquals("Curitiba", responseDTOResponseEntity.getBody().getCidade());
        Assertions.assertEquals("PR", responseDTOResponseEntity.getBody().getUF());
        Assertions.assertEquals("Rua Renato Polatti", responseDTOResponseEntity.getBody().getEndereco());
    }

    @Test
    void whenGetRecentSearchesZipCodeReturnTop10RegistersSuccess() {
        Mockito.when(zipCodeSearchService.getRecentZipCodeSearches()).thenReturn(this.recentZipCodeSearches);
        Mockito.when(recentZipCodeSearchMapper.map(Mockito.any())).thenReturn(this.recentZipCodeSearches);
        ResponseEntity<List<ZipcodeSearchHistoryDTO>> responseListRecenteZipCodeSearch = zipCodeController.getRecentSearchZipCode();

        Assertions.assertNotNull(responseListRecenteZipCodeSearch);
        Assertions.assertNotNull(responseListRecenteZipCodeSearch.getBody());
        Assertions.assertNotNull(responseListRecenteZipCodeSearch.getStatusCode());
        Assertions.assertEquals(HttpStatus.OK, responseListRecenteZipCodeSearch.getStatusCode());
        Assertions.assertEquals(ResponseEntity.class, responseListRecenteZipCodeSearch.getClass());

        Assertions.assertEquals(CEP, responseListRecenteZipCodeSearch.getBody().get(0).getZipCode());
        Assertions.assertEquals(1, responseListRecenteZipCodeSearch.getBody().get(0).getNumberQueries());
    }

    private void startZipCodeSearchResponseDTO(){
        zipCodeSearchResponseDTO = new SearchZipcodeDTO("81230170", "PR","Campo Comprido", "Curitiba", "Rua Renato Polatti" );
    }

    private void startListRecentZipCodeSearch(){
        recentZipCodeSearches = new ArrayList<>(){{
            add(new ZipcodeSearchHistoryDTO(null, CEP, LocalDateTime.now(), 1));
        }};
    }
}