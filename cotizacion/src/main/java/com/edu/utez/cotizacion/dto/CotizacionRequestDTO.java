package com.edu.utez.cotizacion.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CotizacionRequestDTO {

    @NotBlank(message = "El nombre del cliente es obligatorio.")
    private String nombreCliente;

    @NotNull(message = "La edad del conductor es obligatoria.")
    @Min(value = 18, message = "El conductor debe tener al menos 18 años.")
    private Integer edadConductor;

    @NotBlank(message = "El tipo de vehículo es obligatorio.")
    @Pattern(regexp = "^(?i)(COMPACTO|SEDAN|SUV|CAMIONETA)$", message = "El tipo de vehículo debe ser COMPACTO, SEDAN, SUV o CAMIONETA.")
    private String tipoVehiculo;

    @NotNull(message = "Los días de renta son obligatorios.")
    @Min(value = 1, message = "Los días de renta deben ser al menos 1.")
    @Max(value = 30, message = "La renta no puede superar los 30 días.")
    private Integer diasRenta;

    @NotNull(message = "Los kilómetros estimados son obligatorios.")
    @Min(value = 0, message = "Los kilómetros estimados no pueden ser negativos.")
    @Max(value = 5000, message = "Los kilómetros estimados no pueden superar los 5,000 km.")
    private Integer kilometrosEstimados;

    @NotNull(message = "La indicación de seguro completo es obligatoria.")
    private Boolean seguroCompleto;
}