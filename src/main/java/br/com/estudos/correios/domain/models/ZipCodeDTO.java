package br.com.estudos.correios.domain.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ZipCodeDTO {

    @Length(min = 8)
    @NotBlank @NotNull
    @Pattern(regexp = "^[0-9]{8}$", message = "Valor de CEP inválido!")
    @JsonProperty(value = "cep", required = true)
    @ApiModelProperty(value = "CEP no formato 99999999")
    private String CEP;

}
