package com.educarso.simulador.model;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static com.google.common.base.Predicates.or;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.educarso.simulador.resources"))
				.paths(caminho()).build().apiInfo(metaData());
	}

	 @SuppressWarnings("unchecked")
	private Predicate<String> caminho() {
	        return or( regex("/processos.*"),regex("/escalonador.*"),regex("/politicas.*")
	        );
	}

	

	private ApiInfo metaData() {
		ApiInfo apiInfo = new ApiInfo("Arquitetura Sistemas Operacionais", "Projeto TCC - CEFET MG", "1.0",
				"Termos de serviço", new Contact("Débora Cristina Ferreira", null, "deboracrisf91@gmail.com"), "", "");
		return apiInfo;
	}
}
