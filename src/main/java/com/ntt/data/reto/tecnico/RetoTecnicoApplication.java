package com.ntt.data.reto.tecnico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class RetoTecnicoApplication {
    public static void main(String[] args) {
        SpringApplication.run(RetoTecnicoApplication.class, args);
    }
}
