package br.com.estudos.correios.initializer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.domain.EntityScanPackages;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "br.com.estudos.correios.*")
@EntityScan(basePackages = "br.com.estudos.correios.*")
@EnableJpaRepositories(basePackages = "br.com.estudos.correios.*")
public class CorreiosApplication {

    public static void main(String[] args) {
        SpringApplication.run(CorreiosApplication.class, args);
    }

}
