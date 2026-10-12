package com.edu.utez.cotizacion.exception;

public class EnvioNoPermitidoException extends RuntimeException {
    public EnvioNoPermitidoException(String mensaje) {
        super(mensaje);
    }
}