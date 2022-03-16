package br.com.estudos.correios.controllers;

import br.com.estudos.correios.domain.exception.ObjectNotFound;
import br.com.estudos.correios.domain.exception.SQLErrorSintaxException;
import br.com.estudos.correios.domain.models.ErrorDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ErrorHandlingController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { ObjectNotFound.class})
    protected ResponseEntity<Object> handleObjectNotFound(
            RuntimeException ex, WebRequest request) {
        ErrorDTO error = new ErrorDTO(LocalDateTime.now(), ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return handleExceptionInternal(ex, error,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = { SQLErrorSintaxException.class})
    protected ResponseEntity<Object> handleSQLErrorSintaxException(
            RuntimeException ex, WebRequest request) {
        ErrorDTO error = new ErrorDTO(LocalDateTime.now(), ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return handleExceptionInternal(ex, error,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
