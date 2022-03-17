package br.com.estudos.correios.webServices;

import br.com.estudos.correios.initializer.CorreiosApplication;
import br.com.webservices.correios.stub.ConsultaCEPResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = CorreiosApplication.class)
class SearchZipCodeClientTest {

    private static final String CEP = "81230170";
    private static final String CIDADE = "Curitiba";
    private static final String UF = "PR";
    private static final String BAIRRO = "Campo Comprido";
    private static final String ENDERECO = "Rua Renato Polatti";

    @Autowired
    private SearchZipCodeClient searchZipCodeClient;

    @Test
    public void whenSendZipcodeIntoServicesThenReturnResponseOfServices() {
        ConsultaCEPResponse response = searchZipCodeClient.getCEP(CEP);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(CEP, response.getReturn().getCep());
        Assertions.assertEquals(CEP.length(), response.getReturn().getCep().length());
        Assertions.assertEquals(CIDADE, response.getReturn().getCidade());
        Assertions.assertEquals(UF, response.getReturn().getUf());
        Assertions.assertEquals(ENDERECO, response.getReturn().getEnd());
        Assertions.assertEquals(BAIRRO, response.getReturn().getBairro());
    }
}