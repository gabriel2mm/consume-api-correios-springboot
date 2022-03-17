package br.com.estudos.correios.domain.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ZipCodeSearchResponseDTO {

    @JsonProperty("cep")
    @ApiModelProperty(value = "CEP no formato 99999999")
    public String CEP;

    @JsonProperty("uf")
    @ApiModelProperty(value = "UF no formato XX")
    public String UF;

    @JsonProperty("bairro")
    @ApiModelProperty(value = "Nome do bairro")
    public String bairro;

    @JsonProperty("cidade")
    @ApiModelProperty(value = "Nome da cidade")
    public String cidade;

    @JsonProperty("endereco")
    @ApiModelProperty(value = "Endereço")
    public String endereco;
}
