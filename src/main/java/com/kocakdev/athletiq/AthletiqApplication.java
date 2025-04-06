package com.kocakdev.athletiq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@ComponentScan(basePackages = "com.kocakdev.athletiq")
@SpringBootApplication
public class AthletiqApplication {

	public static void main(String[] args) {
		SpringApplication.run(AthletiqApplication.class, args);
	}

}