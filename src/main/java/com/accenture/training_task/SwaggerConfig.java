package com.accenture.training_task;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	public static final Contact CONTACT = new Contact("Team 2", "", "");

	public static final ApiInfo API_INFO = new ApiInfo(
			"Flight Data API", "API that allows to see current flights and save favourite ones",
			"1.0", "", CONTACT, "Apache 2.0", "https://www.apache.org/licenses/LICENSE-2.0");
	
	private static final Set<String> PRODUCES_AND_CONSUMES_INFO = new HashSet<String>(Arrays.asList("application/json"));
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(API_INFO)
				.produces(PRODUCES_AND_CONSUMES_INFO)
				.consumes(PRODUCES_AND_CONSUMES_INFO);
	}
}
