package com.edu.utez.cotizacion.service;

import com.edu.utez.cotizacion.dto.HospedajeRequestDTO;
import com.edu.utez.cotizacion.dto.HospedajeResponseDTO;
import com.edu.utez.cotizacion.exception.HospedajeNoPermitidoException;
import org.springframework.stereotype.Service;

@Service
public class HospedajeService {

    public HospedajeResponseDTO calcularHospedaje(HospedajeRequestDTO request) {
        String habitacion = request.getTipoHabitacion().toUpperCase();
        String temporada = request.getTemporada().toUpperCase();

        if (request.getNumeroNoches() > 30) {
            throw new HospedajeNoPermitidoException("El número de noches no puede superar los 30 días.");
        }

        double costoNoche;
        int capacidadMaxima;

        switch (habitacion) {
            case "INDIVIDUAL":
                costoNoche = 700;
                capacidadMaxima = 1;
                break;
            case "DOBLE":
                costoNoche = 1100;
                capacidadMaxima = 2;
                break;
            case "SUITE":
                costoNoche = 1800;
                capacidadMaxima = 4;
                break;
            default:
                throw new HospedajeNoPermitidoException("Tipo de habitación no válido.");
        }

        if (request.getNumeroHuespedes() > capacidadMaxima) {
            throw new HospedajeNoPermitidoException("El número de huéspedes supera la capacidad máxima de la habitación (" + capacidadMaxima + " persona(s)).");
        }

        double costoHospedajeBase = costoNoche * request.getNumeroNoches();

        double ajusteTemporada = 0;
        if (temporada.equals("BAJA")) {
            ajusteTemporada = - (costoHospedajeBase * 0.10);
        } else if (temporada.equals("ALTA")) {
            ajusteTemporada = costoHospedajeBase * 0.25;
        } else if (!temporada.equals("REGULAR")) {
            throw new HospedajeNoPermitidoException("Temporada no válida.");
        }

        double costoHospedajeAjustado = costoHospedajeBase + ajusteTemporada;

        double descuentoLargaEstancia = 0;
        if (request.getNumeroNoches() >= 7) {
            descuentoLargaEstancia = costoHospedajeBase * 0.08;
        }

        double costoDesayuno = 0;
        if (Boolean.TRUE.equals(request.getIncluyeDesayuno())) {
            costoDesayuno = request.getNumeroHuespedes() * request.getNumeroNoches() * 150;
        }

        double costoEstacionamiento = 0;
        if (Boolean.TRUE.equals(request.getIncluyeEstacionamiento())) {
            costoEstacionamiento = request.getNumeroNoches() * 100;
        }

        double subtotal = (costoHospedajeAjustado - descuentoLargaEstancia) + costoDesayuno + costoEstacionamiento;

        double impuestoHospedaje = subtotal * 0.04;

        double totalPagar = subtotal + impuestoHospedaje;

        return new HospedajeResponseDTO(
                request.getNombreHuesped(),
                habitacion,
                request.getNumeroNoches(),
                request.getNumeroHuespedes(),
                temporada,
                redondear(costoHospedajeBase),
                redondear(ajusteTemporada),
                redondear(descuentoLargaEstancia),
                redondear(costoDesayuno),
                redondear(costoEstacionamiento),
                redondear(subtotal),
                redondear(impuestoHospedaje),
                redondear(totalPagar)
        );
    }

    private double redondear(double valor) {
        return Math.round(valor * 100.0) / 100.0;
    }
}