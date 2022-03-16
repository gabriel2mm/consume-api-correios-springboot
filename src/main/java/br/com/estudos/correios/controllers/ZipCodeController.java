package br.com.estudos.correios.controllers;

import br.com.estudos.correios.domain.entity.RecentZipCodeSearch;
import br.com.estudos.correios.domain.models.ZipCodeDTO;
import br.com.estudos.correios.services.ZipCodeSearchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/CEP")
public class ZipCodeController {

    private final ZipCodeSearchService cepService;

    public ZipCodeController(ZipCodeSearchService cepService) {
        this.cepService = cepService;
    }

    @PostMapping
    public @ResponseBody ResponseEntity searchByCep(@Validated @RequestBody ZipCodeDTO cepDTO){
        return ResponseEntity.status(HttpStatus.OK).body(cepService.searchZipCode(cepDTO.getCEP()));
    }

    @GetMapping
    public @ResponseBody ResponseEntity<List<RecentZipCodeSearch>> getRecentSearchZipCode(){
        return ResponseEntity.status(HttpStatus.OK).body(cepService.getRecentZipCodeSearches());
    }
}