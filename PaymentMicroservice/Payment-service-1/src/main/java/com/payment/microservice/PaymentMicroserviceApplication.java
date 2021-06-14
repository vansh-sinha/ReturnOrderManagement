package com.payment.microservice;

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

@SpringBootApplication
@ComponentScan(basePackages = "com.payment.microservice")
@EnableFeignClients
@EnableSwagger2
public class PaymentMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentMicroserviceApplication.class, args);
	}
	
	@Bean
	public Docket configSwagger2(){
		return  new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.payment.microservice"))
				.paths(PathSelectors.ant("/*"))
				.build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo(){
		return new ApiInfo(
				"Payment Microservice",
				"Return order  management microservice",
				"1.0",
				"https://anything.com/tos",
				new Contact("Abhishek", "https://anysong.com", "abhishek.k.com"),
				"FSE",
				"https://anything.com/fse",
				Collections.emptyList() 
		);
	} 

}
