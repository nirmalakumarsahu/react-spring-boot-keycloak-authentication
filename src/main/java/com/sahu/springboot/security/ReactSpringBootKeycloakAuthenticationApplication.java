package com.sahu.springboot.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ReactSpringBootKeycloakAuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactSpringBootKeycloakAuthenticationApplication.class, args);
	}

}
