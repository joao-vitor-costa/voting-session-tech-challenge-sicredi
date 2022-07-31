package com.joao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.joao")
public class AssociateRegistrationManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssociateRegistrationManagerApplication.class, args);
    }

}
