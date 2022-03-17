package br.com.estudos.correios.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorDTO {

    private LocalDateTime timestamp;
    private String message;
    private int statusCode;
}
