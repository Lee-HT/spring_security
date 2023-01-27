package com.lagrange.infi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class InfiApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfiApplication.class, args);
	}

}
