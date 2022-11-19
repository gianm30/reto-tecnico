package com.ntt.data.reto.tecnico.exception;

public class DesencriptacionException extends RuntimeException {
    public DesencriptacionException(String mensaje) {
        super(mensaje);
    }

    public DesencriptacionException(Throwable e) {
        super(e);
    }
}
