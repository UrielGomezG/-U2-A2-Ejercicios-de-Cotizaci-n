package com.edu.utez.cotizacion.service;

import com.edu.utez.cotizacion.dto.CotizacionRequestDTO;
import com.edu.utez.cotizacion.dto.CotizacionResponseDTO;
import com.edu.utez.cotizacion.exception.RentaNoPermitidaException;
import org.springframework.stereotype.Service;

@Service
public class CotizadorService {

    public CotizacionResponseDTO calcularCotizacion(CotizacionRequestDTO request) {
        String tipo = request.getTipoVehiculo().toUpperCase();

        if (request.getEdadConductor() < 18) {
            throw new RentaNoPermitidaException("El conductor debe tener al menos 18 años.");
        }
        if (request.getDiasRenta() > 30) {
            throw new RentaNoPermitidaException("La renta no puede superar los 30 días.");
        }
        if (request.getKilometrosEstimados() > 5000) {
            throw new RentaNoPermitidaException("Los kilómetros estimados no pueden superar los 5,000 km.");
        }
        if (tipo.equals("CAMIONETA") && request.getEdadConductor() < 25) {
            throw new RentaNoPermitidaException("No se permite rentar camioneta a conductores menores de 25 años.");
        }

        double tarifaDiaria;
        switch (tipo) {
            case "COMPACTO":
                tarifaDiaria = 550;
                break;
            case "SEDAN":
                tarifaDiaria = 700;
                break;
            case "SUV":
                tarifaDiaria = 950;
                break;
            case "CAMIONETA":
                tarifaDiaria = 1200;
                break;
            default:
                throw new RentaNoPermitidaException("Tipo de vehículo no válido.");
        }

        double costoRentaBase = tarifaDiaria * request.getDiasRenta();

        double kmIncluidos = request.getDiasRenta() * 100;
        double costoKmAdicionales = 0;
        if (request.getKilometrosEstimados() > kmIncluidos) {
            double kmExtras = request.getKilometrosEstimados() - kmIncluidos;
            costoKmAdicionales = kmExtras * 4;
        }

        double cargoPorEdad = 0;
        if (request.getEdadConductor() >= 18 && request.getEdadConductor() <= 24) {
            cargoPorEdad = (costoRentaBase + costoKmAdicionales) * 0.15;
        }

        double costoSeguro = 0;
        if (Boolean.TRUE.equals(request.getSeguroCompleto())) {
            costoSeguro = request.getDiasRenta() * 180;
        }

        double descuentoRenta = 0;
        if (request.getDiasRenta() >= 7) {
            descuentoRenta = costoRentaBase * 0.10;
        }

        double totalPagar = (costoRentaBase - descuentoRenta) + costoKmAdicionales + cargoPorEdad + costoSeguro;

        return new CotizacionResponseDTO(
                request.getNombreCliente(),
                tipo,
                request.getDiasRenta(),
                redondear(costoRentaBase),
                redondear(descuentoRenta),
                redondear(costoKmAdicionales),
                redondear(cargoPorEdad),
                redondear(costoSeguro),
                redondear(totalPagar)
        );
    }

    private double redondear(double valor) {
        return Math.round(valor * 100.0) / 100.0;
    }
}