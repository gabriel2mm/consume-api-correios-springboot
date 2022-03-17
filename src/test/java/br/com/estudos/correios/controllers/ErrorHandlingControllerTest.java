package br.com.estudos.correios.controllers;

import br.com.estudos.correios.domain.exception.ObjectNotFound;
import br.com.estudos.correios.domain.exception.SQLErrorSintaxException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.WebRequest;

import java.security.Principal;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
class ErrorHandlingControllerTest {

    @InjectMocks
    private ErrorHandlingController errorHandlingController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenObjectNotFoundExceptionThenResponseEntityObject() {
        ResponseEntity<Object> responseEntity = errorHandlingController.handleObjectNotFound(
                new ObjectNotFound("Objeto não encontrado!"), null);
        Assertions.assertNotNull(responseEntity);
        Assertions.assertNotNull(responseEntity.getBody());
        Assertions.assertNotNull(responseEntity.getStatusCode());
        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        Assertions.assertEquals(ResponseEntity.class, responseEntity.getClass());
    }

    @Test
    void whenSQLErrorSintaxExceptionExceptionThenResponseEntityObject() {
        ResponseEntity<Object> responseEntity = errorHandlingController.handleSQLErrorSintaxException(
                new SQLErrorSintaxException("Registro duplicado!"), null);
        Assertions.assertNotNull(responseEntity);
        Assertions.assertNotNull(responseEntity.getBody());
        Assertions.assertNotNull(responseEntity.getStatusCode());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        Assertions.assertEquals(ResponseEntity.class, responseEntity.getClass());
    }
}