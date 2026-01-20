package com.avwaveaf.accounts;

import com.avwaveaf.accounts.constants.BeansConst;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = BeansConst.AUDIT_AWARE_IMPL)
@OpenAPIDefinition(
        info = @Info(
                title = "Customer-Accounts API Documentation",
                description = "This is Part of Customer Accounts Service in Spring Microservice API Documentation.",
                version = "v1",
                contact = @Contact(
                        name = "Dev teams",
                        email = "-",
                        url = "github.com/avwaveaf"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "github.com/avwaveaf"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Whole Orlantis Bank Microservice REST API Documentation",
                url = "TBA"
        )
)
public class AccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }

}
