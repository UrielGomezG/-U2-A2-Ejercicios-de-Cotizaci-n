package com.edu.utez.cotizacion.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CotizacionResponseDTO {

    private String nombreCliente;
    private String tipoVehiculo;
    private Integer diasRenta;
    private double costoRentaBase;
    private double descuentoRenta;
    private double costoKmAdicionales;
    private double cargoPorEdad;
    private double costoSeguro;
    private double totalPagar;
}