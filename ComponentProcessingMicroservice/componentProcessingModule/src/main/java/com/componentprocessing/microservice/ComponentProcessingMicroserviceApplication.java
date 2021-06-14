package com.componentprocessing.microservice;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@EnableFeignClients
@ComponentScan(basePackages = "com.componentprocessing.microservice")
public class ComponentProcessingMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComponentProcessingMicroserviceApplication.class, args);
	}

	@Bean
	public Docket configureSwagger2(){
		return new Docket(DocumentationType.SWAGGER_2)
					.select()
					.paths(PathSelectors.ant("/componentprocessingmodule/*"))
					.apis(RequestHandlerSelectors.basePackage("com.componentprocessing.microservice"))
			
					.build()
					.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo(){
		return new ApiInfo(
				"Component Processing Module",
				"Return Order Management Microservice",
				"1.0",
				"Adyasha Tarasia - Programmer Analyst Trainee || Full Stack ...in.linkedin.com",
				new Contact("Adyasha Tarasia", "something.com","Adyasha.Tarasia@cognizant.com"),
				"FSE", "https://hello.adyasha.com",
				Collections.emptyList()
		);
	}

}
