package com.ntt.data.reto.cliente.backend.service.impl;

import com.ntt.data.reto.cliente.backend.bean.EncriptacionBean;
import com.ntt.data.reto.cliente.backend.entity.Cliente;
import com.ntt.data.reto.cliente.backend.exception.CodigoUnicoNoEnviadoException;
import com.ntt.data.reto.cliente.backend.exception.EntidadNoEncontradaException;
import com.ntt.data.reto.cliente.backend.exception.YaExisteException;
import com.ntt.data.reto.cliente.backend.model.ClienteDTO;
import com.ntt.data.reto.cliente.backend.repository.ClienteRepository;
import com.ntt.data.reto.cliente.backend.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    private ClienteRepository repository;

    @Autowired
    private EncriptacionBean encriptacion;

    @Override
    public List<ClienteDTO> listar() {
        return repository.findAll().stream().filter(Objects::nonNull).map(this::convertirEnDTO).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public ClienteDTO obtenerClientePorCodigoUnico(String codigoUnico) {
        return convertirEnDTO(repository.findById(desencriptar(codigoUnico)).orElseThrow(() -> new EntidadNoEncontradaException()));
    }

    @Override
    public ClienteDTO crear(ClienteDTO clienteDTO) {
        if(Optional.ofNullable(clienteDTO.getCodigoUnico()).map(String::trim).map(String::isEmpty).orElse(true))
            throw new CodigoUnicoNoEnviadoException();

        Optional<Cliente> entidad = repository.findById(clienteDTO.getCodigoUnico());
        if(entidad.isPresent())
            throw new YaExisteException();
        return convertirEnDTO(repository.save(convertirEnEntidad(clienteDTO)));
    }

    @Override
    public ClienteDTO modificar(ClienteDTO clienteDTO) {
        ClienteDTO dto = obtenerClientePorCodigoUnico(clienteDTO.getCodigoUnico());
        clienteDTO.setCodigoUnico(dto.getCodigoUnico());
        return convertirEnDTO(repository.save(convertirEnEntidad(clienteDTO)));
    }

    @Override
    public void eliminar(String codigoUnico) {
        repository.deleteById(desencriptar(codigoUnico));
    }

    private String desencriptar(String codigoUnico) {
        return encriptacion.desencriptar(codigoUnico);
    }

    private ClienteDTO convertirEnDTO(Cliente x) {
        return ClienteDTO.builder()//
                .codigoUnico(x.getCodigoUnico())//
                .nombre(x.getNombre())//
                .apellidos(x.getApellidos())//
                .tipoDocumento(x.getTipoDocumento())//
                .numeroDocumento(x.getNumeroDocumento())//
                .build();
    }

    private Cliente convertirEnEntidad(ClienteDTO x) {
        return Cliente.builder()//
                .codigoUnico(x.getCodigoUnico())//
                .nombre(x.getNombre())//
                .apellidos(x.getApellidos())//
                .tipoDocumento(x.getTipoDocumento())//
                .numeroDocumento(x.getNumeroDocumento())//
                .build();
    }
}
