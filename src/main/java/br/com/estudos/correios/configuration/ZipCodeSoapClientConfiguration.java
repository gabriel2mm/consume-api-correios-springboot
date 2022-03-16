package br.com.estudos.correios.configuration;

import br.com.estudos.correios.webServices.SearchZipCodeClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class ZipCodeSoapClientConfiguration {

    private static final String PACKAGE_SCAN = "br.com.webservices.correios.stub";
    private static final String SERVICE_ENDPOINT = "https://apphom.correios.com.br/SigepMasterJPA/AtendeClienteService/AtendeCliente?wsdl";

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath(PACKAGE_SCAN);
        return marshaller;
    }

    @Bean
    public SearchZipCodeClient searchZipCodeClient(Jaxb2Marshaller marshaller) {
        SearchZipCodeClient client = new SearchZipCodeClient();
        client.setDefaultUri(SERVICE_ENDPOINT);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
