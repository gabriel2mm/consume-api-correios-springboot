package br.com.estudos.correios.controllers;

import br.com.estudos.correios.domain.dtos.ZipcodeSearchHistoryDTO;
import br.com.estudos.correios.domain.dtos.ZipcodeDTO;
import br.com.estudos.correios.domain.dtos.SearchZipcodeDTO;
import br.com.estudos.correios.services.ZipCodeSearchService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/zip-code")
public class ZipCodeController {

    private final ZipCodeSearchService zipCodeSearchService;

    public ZipCodeController(ZipCodeSearchService zipCodeSearchService) {
        this.zipCodeSearchService = zipCodeSearchService;
    }

    @ApiOperation(value = "Retorna informações de endereço baseado no CEP.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna informações de endereço baseado no CEP."),
            @ApiResponse(code = 404, message = "CEP não foi localizado no serviço."),
            @ApiResponse(code = 400, message = "Erro ao salvar informações no duplicadas no banco de dados."),
    })
    @PostMapping
    public ResponseEntity<SearchZipcodeDTO> searchByZipCode(@Validated @RequestBody ZipcodeDTO zipCodeDTO){
        log.debug(MessageFormat.format("Chamando a consulta de cep : {0}.", zipCodeDTO.getZipcode()));
        return ResponseEntity.status(HttpStatus.OK).body(zipCodeSearchService.searchZipCode(zipCodeDTO.getZipcode()));
    }

    @ApiOperation(value = "Retorna uma lista dos últimos 10 CEPS pesquisados na API.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista com os útimos 10 CEPS pesquisados."),
    })
    @GetMapping
    public ResponseEntity<List<ZipcodeSearchHistoryDTO>> getRecentSearchZipCode(){
        log.debug("Realizando a chamada do histórico de CEP.");
        return ResponseEntity.status(HttpStatus.OK).body(zipCodeSearchService.getRecentZipCodeSearches());
    }
}
