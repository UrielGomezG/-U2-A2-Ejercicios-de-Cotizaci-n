package com.edu.utez.cotizacion.controller;

import com.edu.utez.cotizacion.dto.HospedajeRequestDTO;
import com.edu.utez.cotizacion.dto.HospedajeResponseDTO;
import com.edu.utez.cotizacion.service.HospedajeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hospedaje")
public class HospedajeController {

    private final HospedajeService hospedajeService;

    public HospedajeController(HospedajeService hospedajeService) {
        this.hospedajeService = hospedajeService;
    }

    @PostMapping
    public ResponseEntity<HospedajeResponseDTO> cotizarHospedaje(@Valid @RequestBody HospedajeRequestDTO request) {
        HospedajeResponseDTO respuesta = hospedajeService.calcularHospedaje(request);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
}