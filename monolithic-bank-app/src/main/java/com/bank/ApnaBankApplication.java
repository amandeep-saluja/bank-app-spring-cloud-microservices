package com.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:message.properties")
public class ApnaBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApnaBankApplication.class, args);
	}

}
