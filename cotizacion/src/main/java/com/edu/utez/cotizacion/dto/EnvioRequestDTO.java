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
public class EnvioRequestDTO {

    @NotBlank(message = "El código postal es obligatorio.")
    private String codigoPostal;

    @NotNull(message = "El peso es obligatorio.")
    @Min(value = 0, message = "El peso no puede ser negativo.")
    @Max(value = 50, message = "El paquete no puede pesar más de 50 kg.")
    private Double pesoKg;

    @NotNull(message = "El largo es obligatorio.")
    @Min(value = 1, message = "El largo debe ser mayor a 0.")
    @Max(value = 150, message = "El largo no puede superar los 150 cm.")
    private Double largoCm;

    @NotNull(message = "El ancho es obligatorio.")
    @Min(value = 1, message = "El ancho debe ser mayor a 0.")
    @Max(value = 150, message = "El ancho no puede superar los 150 cm.")
    private Double anchoCm;

    @NotNull(message = "El alto es obligatorio.")
    @Min(value = 1, message = "El alto debe ser mayor a 0.")
    @Max(value = 150, message = "El alto no puede superar los 150 cm.")
    private Double altoCm;

    @NotBlank(message = "El tipo de envío es obligatorio.")
    @Pattern(regexp = "^(?i)(ESTANDAR|EXPRESS|MISMO_DIA)$", message = "El tipo de envío debe ser ESTANDAR, EXPRESS o MISMO_DIA.")
    private String tipoEnvio;

    @NotNull(message = "El valor declarado es obligatorio.")
    @Min(value = 0, message = "El valor declarado no puede ser negativo.")
    private Double valorDeclarado;
}