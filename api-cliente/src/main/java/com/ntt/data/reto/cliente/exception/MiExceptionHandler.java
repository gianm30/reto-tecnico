package com.ntt.data.reto.cliente.exception;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.Map;

@RestControllerAdvice
public class MiExceptionHandler {
    @ExceptionHandler({HttpClientErrorException.class, HttpServerErrorException.class})
    public ResponseEntity<?> errorConsumiendoCliente(HttpStatusCodeException exception) {
        String s = exception.getResponseBodyAsString();

        if(s.startsWith("["))
            return new ResponseEntity<>(new JSONArray(s).toList(), HttpStatus.BAD_REQUEST);
        else {
            s = s.substring(s.indexOf(0) + 1);
            if(s.startsWith("{"))
                return new ResponseEntity<>(new JSONObject(s).toMap(), HttpStatus.BAD_REQUEST);
            else
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> errorDesencriptando(Exception exception) {
        String s = exception.getMessage();
        return new ResponseEntity<>(s, HttpStatus.BAD_REQUEST);
    }
}
