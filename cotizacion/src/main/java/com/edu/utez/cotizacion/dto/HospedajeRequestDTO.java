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
public class HospedajeRequestDTO {

    @NotBlank(message = "El nombre del huésped es obligatorio.")
    private String nombreHuesped;

    @NotBlank(message = "El tipo de habitación es obligatorio.")
    @Pattern(regexp = "^(?i)(INDIVIDUAL|DOBLE|SUITE)$", message = "El tipo de habitación debe ser INDIVIDUAL, DOBLE o SUITE.")
    private String tipoHabitacion;

    @NotNull(message = "El número de noches es obligatorio.")
    @Min(value = 1, message = "El número de noches debe ser al menos 1.")
    @Max(value = 30, message = "El número de noches no puede superar los 30.")
    private Integer numeroNoches;

    @NotNull(message = "El número de huéspedes es obligatorio.")
    @Min(value = 1, message = "Debe haber al menos 1 huésped.")
    private Integer numeroHuespedes;

    @NotBlank(message = "La temporada es obligatoria.")
    @Pattern(regexp = "^(?i)(BAJA|REGULAR|ALTA)$", message = "La temporada debe ser BAJA, REGULAR o ALTA.")
    private String temporada;

    @NotNull(message = "La indicación de desayuno es obligatoria.")
    private Boolean incluyeDesayuno;

    @NotNull(message = "La indicación de estacionamiento es obligatoria.")
    private Boolean incluyeEstacionamiento;
}