package com.edu.utez.cotizacion.exception;

public class RentaNoPermitidaException extends RuntimeException {
    public RentaNoPermitidaException(String mensaje) {
        super(mensaje);
    }
}