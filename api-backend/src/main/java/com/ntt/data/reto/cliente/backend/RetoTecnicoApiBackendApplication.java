package com.ntt.data.reto.cliente.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class RetoTecnicoApiBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(RetoTecnicoApiBackendApplication.class, args);
    }
}
