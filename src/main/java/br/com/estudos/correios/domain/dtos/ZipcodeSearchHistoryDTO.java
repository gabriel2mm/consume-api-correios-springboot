package br.com.estudos.correios.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ZipcodeSearchHistoryDTO {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long Id;

    @ApiModelProperty(value = "CEP no formato 8 digitos.")
    @JsonProperty(value = "CEP")
    private String zipCode;

    @JsonProperty
    @ApiModelProperty(value = "Data da última pesquisa.")
    private LocalDateTime updated;

    @JsonProperty
    @ApiModelProperty(value = "Quantidade de pesquisas.")
    private int numberQueries;

}
