package com.ntt.data.reto.cliente.controller;

import com.ntt.data.reto.cliente.model.vo.ClienteVO;
import com.ntt.data.reto.cliente.util.RestTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/")
@SuppressWarnings("unused")
public class ClienteRestController {
    @Autowired
    private RestTemplateUtil restTemplateUtil;

    @Value("${api.cliente.encriptar}")
    private String urlEncriptar;

    @Value("${api.cliente.listar}")
    private String urlListar;

    @Value("${api.cliente.obtenerClientePorCodigoUnico}")
    private String urlObtenerClientePorCodigoUnico;

    @Value("${api.cliente.crear}")
    private String urlCrear;

    @Value("${api.cliente.modificar}")
    private String urlModificar;

    @Value("${api.cliente.eliminar}")
    private String urlEliminar;

    @GetMapping("/encriptar/{codigoUnico}")
    public ResponseEntity<?> encriptar(@PathVariable String codigoUnico) {
        return restTemplateUtil.get(urlEncriptar.replace("{codigo_unico}", codigoUnico));
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        return restTemplateUtil.getList(urlListar);
    }

    @GetMapping("/id")
    public ResponseEntity<?> obtenerClientePorCodigoUnico(@RequestParam(name = "codigo_unico") String codigoUnico) {
        return restTemplateUtil.get(urlObtenerClientePorCodigoUnico.replace("{codigo_unico}", codigoUnico));
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody ClienteVO clienteVO, BindingResult validacion) {
        return restTemplateUtil.post(urlCrear, clienteVO);
    }

    @PutMapping
    public ResponseEntity<?> modificar(@Valid @RequestBody ClienteVO clienteVO, BindingResult validacion) {
        return restTemplateUtil.put(urlModificar, clienteVO);
    }

    @DeleteMapping
    public ResponseEntity<?> eliminar(@RequestParam(name = "codigo_unico") String codigoUnico) {
        return restTemplateUtil.delete(urlEliminar.replace("{codigo_unico}", codigoUnico));
    }
}
