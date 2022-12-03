package com.rso.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.rso.microservice", "com.rso.microservice.api", "com.rso.microservice.api.mapper"})
public class FavoriteProductsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FavoriteProductsApplication.class, args);
	}

}
