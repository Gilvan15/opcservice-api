package com.ggsoftware.opcserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OpcserverApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpcserverApiApplication.class, args);
		System.out.println("Server Up!");
	}

}
