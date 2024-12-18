package com.shaastra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SpringDocOpenAPIConfig 
{
	@Bean
	public OpenAPI openAPI()
	{
		
		return new OpenAPI()
				.info(new Info().title("Shaastra Coding Database")
				.description("A project for storing Shaastra Coding Contest Scores.").version("1.0.0").license(new License().name("Apache")));
				
	}
}
