package com.ntt.data.reto.cliente.backend.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MiExceptionHandler {
    @ExceptionHandler({EntidadNoEncontradaException.class, EmptyResultDataAccessException.class})
    public ResponseEntity<?> entidadNoEncontrada() {
        return new ResponseEntity<>(new MensajeErrorVO("No se encontraron datos"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CodigoUnicoNoEnviadoException.class)
    public ResponseEntity<?> codigoUnicoNoEnviado() {
        return new ResponseEntity<>(new MensajeErrorVO("Código único no enviado"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(YaExisteException.class)
    public ResponseEntity<?> yaExiste() {
        return new ResponseEntity<>(new MensajeErrorVO("Código único ya existe"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DesencriptacionException.class)
    public ResponseEntity<?> errorDesencriptando(DesencriptacionException exception) {
        return new ResponseEntity<>(new MensajeErrorVO(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidacionBodyException.class)
    public ResponseEntity<?> errorDesencriptando(ValidacionBodyException exception) {
        return new ResponseEntity<>(exception.getErrors(), HttpStatus.BAD_REQUEST);
    }
}
