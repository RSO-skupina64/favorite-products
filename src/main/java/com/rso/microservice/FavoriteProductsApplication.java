package com.rso.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication(scanBasePackages = {"com.rso.microservice", "com.rso.microservice.api", "com.rso.microservice.api.mapper"})
@RefreshScope
public class FavoriteProductsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FavoriteProductsApplication.class, args);
	}

}
