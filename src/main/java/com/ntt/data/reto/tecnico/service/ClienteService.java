package com.ntt.data.reto.tecnico.service;

import com.ntt.data.reto.tecnico.model.ClienteDTO;

import java.util.List;

public interface ClienteService {
    List<ClienteDTO> listar();

    ClienteDTO obtenerClientePorCodigoUnico(String codigoUnico);

    ClienteDTO crear(ClienteDTO cliente);

    ClienteDTO modificar(ClienteDTO cliente);

    void eliminar(String codigoUnico);
}