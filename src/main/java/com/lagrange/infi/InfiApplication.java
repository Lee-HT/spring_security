package com.lagrange.infi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//SecurityAutoConfiguration.class를 제외하면 security 자동 설정 off 됨
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class InfiApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfiApplication.class, args);
	}

}
