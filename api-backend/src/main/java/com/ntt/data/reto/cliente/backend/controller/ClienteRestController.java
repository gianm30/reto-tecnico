package com.ntt.data.reto.cliente.backend.controller;

import com.ntt.data.reto.cliente.backend.exception.ValidacionBodyException;
import com.ntt.data.reto.cliente.backend.model.ClienteDTO;
import com.ntt.data.reto.cliente.backend.service.ClienteService;
import com.ntt.data.reto.cliente.backend.bean.EncriptacionBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@RestController("/com/ntt/data/reto/cliente/backend")
@SuppressWarnings("unused")
public class ClienteRestController {
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EncriptacionBean encriptacion;

    @GetMapping("/encriptar/{codigoUnico}")
    public Map<String, Object> encriptar(@PathVariable String codigoUnico) {
        return Collections.singletonMap("codigo_unico", encriptacion.encriptar(codigoUnico));
    }

    @GetMapping("/")
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(clienteService.listar());
    }

    @GetMapping("/id")
    public ResponseEntity<?> obtenerClientePorCodigoUnico(@RequestParam(name = "codigo_unico") String codigoUnico) {
        return ResponseEntity.ok(clienteService.obtenerClientePorCodigoUnico(codigoUnico));
    }

    @PostMapping("/")
    public ResponseEntity<?> crear(@Valid @RequestBody ClienteDTO clienteDTO, BindingResult validacion) {
        validarBody(validacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.crear(clienteDTO));
    }

    @PutMapping("/")
    public ResponseEntity<?> modificar(@Valid @RequestBody ClienteDTO clienteDTO, BindingResult validacion) {
        validarBody(validacion);
        return ResponseEntity.ok(clienteService.modificar(clienteDTO));
    }

    @DeleteMapping("/")
    public ResponseEntity<?> delete(@RequestParam(name = "codigo_unico") String codigoUnico) {
        clienteService.eliminar(codigoUnico);
        return ResponseEntity.noContent().build();
    }

    private void validarBody(BindingResult validacion) {
        if(validacion.hasErrors())
            throw new ValidacionBodyException(validacion.getFieldErrors());
    }
}
