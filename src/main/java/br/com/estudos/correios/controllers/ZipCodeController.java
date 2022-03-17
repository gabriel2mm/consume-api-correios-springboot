package br.com.estudos.correios.controllers;

import br.com.estudos.correios.domain.entity.RecentZipCodeSearch;
import br.com.estudos.correios.domain.mappers.RecentZipCodeSearchMapper;
import br.com.estudos.correios.domain.models.RecentZipCodeSearchDTO;
import br.com.estudos.correios.domain.models.ZipCodeDTO;
import br.com.estudos.correios.domain.models.ZipCodeSearchResponseDTO;
import br.com.estudos.correios.services.ZipCodeSearchService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.List;

@RestController
@RequestMapping("/api/v1/zip-code")
@ApiOperation(value="Api visualizar consulta e histórico de pesquisa de CEP.")
public class ZipCodeController {

    private final ZipCodeSearchService zipCodeSearchService;
    private final RecentZipCodeSearchMapper recentZipCodeSearchMapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(ZipCodeController.class);

    public ZipCodeController(ZipCodeSearchService zipCodeSearchService,
                             RecentZipCodeSearchMapper recentZipCodeSearchMapper) {
        this.zipCodeSearchService = zipCodeSearchService;
        this.recentZipCodeSearchMapper = recentZipCodeSearchMapper;
    }

    @ApiOperation(value = "Retorna informações de endereço baseado no CEP.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna informações de endereço baseado no CEP."),
            @ApiResponse(code = 404, message = "CEP não foi localizado no serviço."),
            @ApiResponse(code = 400, message = "Erro ao salvar informações no duplicadas no banco de dados."),
    })
    @PostMapping
    public @ResponseBody ResponseEntity<ZipCodeSearchResponseDTO> searchByZipCode(@Validated @RequestBody ZipCodeDTO zipCodeDTO){
        LOGGER.debug(MessageFormat.format("Chamando a consulta de cep : {0}.", zipCodeDTO.getCEP()));
        return ResponseEntity.status(HttpStatus.OK).body(zipCodeSearchService.searchZipCode(zipCodeDTO.getCEP()));
    }

    @ApiOperation(value = "Retorna uma lista dos últimos 10 CEPS pesquisados na API.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista com os útimos 10 CEPS pesquisados."),
    })
    @GetMapping
    public @ResponseBody ResponseEntity<List<RecentZipCodeSearchDTO>> getRecentSearchZipCode(){
        LOGGER.debug("Realizando a chamada do histórico de CEP.");
        return ResponseEntity.status(HttpStatus.OK).body(recentZipCodeSearchMapper.map(zipCodeSearchService.getRecentZipCodeSearches()));
    }
}
