package com.avwaveaf.loanservice;

import com.avwaveaf.loanservice.constants.BeansConst;
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
                title = "Loan Service API",
                description = """
                        Loan Service REST API for managing customer loan lifecycle,
                        including loan creation, retrieval, updates, and repayments.
                        
                        This service is part of the eBank microservices ecosystem.
                        """,
                version = "v1.0.0",
                contact = @Contact(
                        name = "eBank Backend Team",
                        email = "backend@ebank.com",
                        url = "https://github.com/Avwaveaf/ebank"
                ),
                license = @License(
                        name = "Apache License 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Loan Service – API Reference & Architecture",
                url = "https://github.com/Avwaveaf/ebank/wiki/loan-service"
        )
)
public class LoanServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoanServiceApplication.class, args);
    }

}
