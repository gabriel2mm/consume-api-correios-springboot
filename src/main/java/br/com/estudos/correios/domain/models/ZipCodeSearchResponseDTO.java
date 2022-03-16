package br.com.estudos.correios.domain.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ZipCodeSearchResponseDTO {

    @JsonProperty("cep")
    public String CEP;

    @JsonProperty("uf")
    public String UF;

    @JsonProperty("bairro")
    public String bairro;

    @JsonProperty("cidade")
    public String cidade;

    @JsonProperty("endereco")
    public String endereco;
}
