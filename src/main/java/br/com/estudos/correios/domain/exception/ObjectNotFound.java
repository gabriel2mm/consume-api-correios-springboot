package br.com.estudos.correios.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ObjectNotFound extends RuntimeException{

    private ObjectNotFound() {
    }

    public ObjectNotFound(String message) {
        super(message);
    }
}
