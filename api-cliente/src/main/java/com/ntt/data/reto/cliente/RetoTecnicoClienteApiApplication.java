package com.ntt.data.reto.cliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class RetoTecnicoClienteApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(RetoTecnicoClienteApiApplication.class, args);
    }
}
