package br.com.estudos.correios.controllers;

import br.com.estudos.correios.domain.entity.RecentZipCodeSearch;
import br.com.estudos.correios.domain.mappers.RecentZipCodeSearchMapper;
import br.com.estudos.correios.domain.models.RecentZipCodeSearchDTO;
import br.com.estudos.correios.domain.models.ZipCodeDTO;
import br.com.estudos.correios.domain.models.ZipCodeSearchResponseDTO;
import br.com.estudos.correios.services.ZipCodeSearchService;
import org.junit.Before;
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

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
class ZipCodeControllerTest {

    @InjectMocks
    private ZipCodeController zipCodeController;

    @Mock
    private ZipCodeSearchService zipCodeSearchService;

    @Mock
    private RecentZipCodeSearchMapper recentZipCodeSearchMapper;

    private ZipCodeSearchResponseDTO zipCodeSearchResponseDTO;

    private List<RecentZipCodeSearch> recentZipCodeSearches;

    private static final String CEP = "81230170";

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        startZipCodeSearchResponseDTO();
        startListRecentZipCodeSearch();
    }

    @Test
    void whenSearchByCepThenReturnCEPInformationSuccess() {
        Mockito.when(zipCodeSearchService.searchZipCode(Mockito.anyString())).thenReturn(this.zipCodeSearchResponseDTO);
        ResponseEntity<ZipCodeSearchResponseDTO> responseDTOResponseEntity = zipCodeController.searchByZipCode(new ZipCodeDTO(CEP));

        Assertions.assertNotNull(responseDTOResponseEntity);
        Assertions.assertNotNull(responseDTOResponseEntity.getBody());
        Assertions.assertNotNull(responseDTOResponseEntity.getStatusCode());
        Assertions.assertEquals(HttpStatus.OK, responseDTOResponseEntity.getStatusCode());
        Assertions.assertEquals(ResponseEntity.class, responseDTOResponseEntity.getClass());
        Assertions.assertEquals(ZipCodeSearchResponseDTO.class, responseDTOResponseEntity.getBody().getClass());

        Assertions.assertEquals(CEP, responseDTOResponseEntity.getBody().getCEP());
        Assertions.assertEquals("Curitiba", responseDTOResponseEntity.getBody().getCidade());
        Assertions.assertEquals("PR", responseDTOResponseEntity.getBody().getUF());
        Assertions.assertEquals("Rua Renato Polatti", responseDTOResponseEntity.getBody().getEndereco());
    }

    @Test
    void whenGetRecentSearchZipCodeReturnTop10RegistersSuccess() {
        Mockito.when(zipCodeSearchService.getRecentZipCodeSearches()).thenReturn(this.recentZipCodeSearches);
        Mockito.when(recentZipCodeSearchMapper.map(Mockito.any())).thenReturn(RecentZipCodeSearchMapper.Instance.map(this.recentZipCodeSearches));
        ResponseEntity<List<RecentZipCodeSearchDTO>> responseListRecenteZipCodeSearch = zipCodeController.getRecentSearchZipCode();

        Assertions.assertNotNull(responseListRecenteZipCodeSearch);
        Assertions.assertNotNull(responseListRecenteZipCodeSearch.getBody());
        Assertions.assertNotNull(responseListRecenteZipCodeSearch.getStatusCode());
        Assertions.assertEquals(HttpStatus.OK, responseListRecenteZipCodeSearch.getStatusCode());
        Assertions.assertEquals(ResponseEntity.class, responseListRecenteZipCodeSearch.getClass());

        Assertions.assertEquals(CEP, responseListRecenteZipCodeSearch.getBody().get(0).getZipCode());
        Assertions.assertEquals(1, responseListRecenteZipCodeSearch.getBody().get(0).getNumberQueries());
    }

    private void startZipCodeSearchResponseDTO(){
        this.zipCodeSearchResponseDTO = new ZipCodeSearchResponseDTO("81230170", "PR","Campo Comprido", "Curitiba", "Rua Renato Polatti" );
    }

    private void startListRecentZipCodeSearch(){
        recentZipCodeSearches = new ArrayList<>(){{
            add(new RecentZipCodeSearch(null, "81230170", LocalDateTime.now(), 1));
        }};
    }
}