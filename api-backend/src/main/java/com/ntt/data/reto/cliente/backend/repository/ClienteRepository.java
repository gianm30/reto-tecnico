package com.ntt.data.reto.cliente.backend.repository;

import com.ntt.data.reto.cliente.backend.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
}
