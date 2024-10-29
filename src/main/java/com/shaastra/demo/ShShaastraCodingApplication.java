package com.shaastra.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.shaastra")

@EntityScan(basePackages = "com.shaastra.entities")
@EnableJpaRepositories(basePackages = "com.shaastra.repositories")

public class ShShaastraCodingApplication 
{
	
	public static void main(String[] args) {
		SpringApplication.run(ShShaastraCodingApplication.class, args);
	}
}