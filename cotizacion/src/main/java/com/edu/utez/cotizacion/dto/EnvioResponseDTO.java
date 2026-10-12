package com.edu.utez.cotizacion.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnvioResponseDTO {

    private String codigoPostal;
    private String tipoEnvio;
    private double volumenCm3;
    private double costoBase;
    private double costoPorPeso;
    private double recargoVolumen;
    private double recargoTipoEnvio;
    private double costoSeguro;
    private double totalPagar;
}