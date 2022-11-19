package com.ntt.data.reto.tecnico.repository;

import com.ntt.data.reto.tecnico.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
}
