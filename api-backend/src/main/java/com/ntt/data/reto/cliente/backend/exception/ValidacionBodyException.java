package com.ntt.data.reto.cliente.backend.exception;

import lombok.Getter;
import org.springframework.validation.FieldError;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ValidacionBodyException extends RuntimeException {
    @Getter
    private final List<Map<String, String>> errors;

    public ValidacionBodyException(List<@NotNull FieldError> lst) {
        Function<FieldError, Map<String, String>> toMap = x -> Collections.singletonMap("error", x.getDefaultMessage());
        errors = lst.stream().map(toMap).collect(Collectors.toList());;
    }
}
