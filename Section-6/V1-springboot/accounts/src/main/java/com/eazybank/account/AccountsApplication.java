package com.eazybank.account;

import com.eazybank.account.dto.AccountContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
/*@ComponentScans({ @ComponentScan("com.eazybytes.accounts.controller") })
@EnableJpaRepositories("com.eazybytes.accounts.repository")
@EntityScan("com.eazybytes.accounts.model")*/
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {AccountContactInfoDto.class})
@OpenAPIDefinition(
		info = @Info(
				title = "Account microservice REST API Documentation",
				description = "Banking Application Accounts microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Amit Kumar Parouha",
						email = "amitparouha010@gmail.com",
						url = "https://github.com/AmitParouha"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://github.com/AmitParouha"
				)
		),
		externalDocs = @ExternalDocumentation(
				description =  "Banking Accounts microservice REST API Documentation",
				url = "https://github.com/AmitParouha/swagger-ui.html"
		)
)
public class AccountsApplication {
	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
