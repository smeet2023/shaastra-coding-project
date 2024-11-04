package com.shaastra.demo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class EnvironmentVariablePrinter {

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @Value("${spring.datasource.username}")
    private String datasourceUsername;

    @Value("${spring.datasource.password}")
    private String datasourcePassword;

    @PostConstruct
    public void printEnvironmentVariables() {
        System.out.println("Datasource URL: " + datasourceUrl);
        System.out.println("Datasource Username: " + datasourceUsername);
        System.out.println("Datasource Password: " + datasourcePassword);
    }
}
