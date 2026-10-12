package com.edu.utez.cotizacion.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HospedajeResponseDTO {

    private String nombreHuesped;
    private String tipoHabitacion;
    private Integer numeroNoches;
    private Integer numeroHuespedes;
    private String temporada;
    private double costoHospedajeBase;
    private double ajusteTemporada;
    private double descuentoLargaEstancia;
    private double costoDesayuno;
    private double costoEstacionamiento;
    private double subtotal;
    private double impuestoHospedaje;
    private double totalPagar;
}