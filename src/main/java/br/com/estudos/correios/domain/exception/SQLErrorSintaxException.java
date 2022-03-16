package br.com.estudos.correios.domain.exception;

import org.springframework.dao.InvalidDataAccessResourceUsageException;

public class SQLErrorSintaxException extends InvalidDataAccessResourceUsageException {

    public SQLErrorSintaxException(String message) {
        super(message);
    }
}
