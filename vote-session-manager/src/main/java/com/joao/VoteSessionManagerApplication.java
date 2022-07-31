package com.joao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.joao")
public class VoteSessionManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoteSessionManagerApplication.class, args);
	}

}
