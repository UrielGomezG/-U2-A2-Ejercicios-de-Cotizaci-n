package com.edu.utez.cotizacion.exception;

public class HospedajeNoPermitidoException extends RuntimeException {
    public HospedajeNoPermitidoException(String mensaje) {
        super(mensaje);
    }
}