package com.ntt.data.reto.cliente.backend.service;

import com.ntt.data.reto.cliente.backend.model.ClienteDTO;

import java.util.List;

public interface ClienteService {
    List<ClienteDTO> listar();

    ClienteDTO obtenerClientePorCodigoUnico(String codigoUnico);

    ClienteDTO crear(ClienteDTO cliente);

    ClienteDTO modificar(ClienteDTO cliente);

    void eliminar(String codigoUnico);
}