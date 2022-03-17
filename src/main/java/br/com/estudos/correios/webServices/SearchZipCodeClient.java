package br.com.estudos.correios.webServices;

import br.com.webservices.correios.stub.ConsultaCEP;
import br.com.webservices.correios.stub.ConsultaCEPResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.xml.bind.*;
import java.text.MessageFormat;

@Slf4j
public class SearchZipCodeClient extends WebServiceGatewaySupport {

    public ConsultaCEPResponse getCEP(final String CEP) {

        log.debug("Iniciando a consulta de CEP");
        ConsultaCEP request = new ConsultaCEP();
        request.setCep(CEP);
        JAXBElement<?> response  = (JAXBElement<?>) getWebServiceTemplate()
                .marshalSendAndReceive(request);
        ConsultaCEPResponse consultaCEPResponse = (ConsultaCEPResponse) response.getValue();
        log.debug("Consulta finalizada");

        return consultaCEPResponse;
    }
}
