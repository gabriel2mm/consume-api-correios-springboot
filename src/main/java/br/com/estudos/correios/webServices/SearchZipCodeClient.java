package br.com.estudos.correios.webServices;

import br.com.webservices.correios.stub.ConsultaCEP;
import br.com.webservices.correios.stub.ConsultaCEPResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.xml.bind.*;
import java.text.MessageFormat;

public class SearchZipCodeClient extends WebServiceGatewaySupport {

    private static final Logger logger = LoggerFactory.getLogger(SearchZipCodeClient.class);

    public ConsultaCEPResponse getCEP(final String CEP) {

        logger.debug("Iniciando a consulta de CEP");
        ConsultaCEP request = new ConsultaCEP();
        request.setCep(CEP);
        JAXBElement<?> response  = (JAXBElement<?>) getWebServiceTemplate()
                .marshalSendAndReceive(request);
        ConsultaCEPResponse consultaCEPResponse = (ConsultaCEPResponse) response.getValue();
        logger.debug("Consulta finalizada");

        return consultaCEPResponse;
    }
}
