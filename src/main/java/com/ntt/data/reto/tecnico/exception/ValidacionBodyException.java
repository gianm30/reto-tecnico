package com.ntt.data.reto.tecnico.exception;

import lombok.Getter;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ValidacionBodyException extends RuntimeException {
    @Getter
    private Map<String, Object> errorMap;

    public ValidacionBodyException(List<FieldError> lst) {
        errorMap = lst.stream().collect(Collectors.toMap(x -> "error", x -> x.getDefaultMessage()));
    }
}
