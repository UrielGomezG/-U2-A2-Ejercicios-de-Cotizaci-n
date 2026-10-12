package com.edu.utez.cotizacion.controller;

import com.edu.utez.cotizacion.dto.CotizacionRequestDTO;
import com.edu.utez.cotizacion.dto.CotizacionResponseDTO;
import com.edu.utez.cotizacion.service.CotizadorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cotizaciones")
public class CotizadorController {

    private final CotizadorService cotizadorService;

    public CotizadorController(CotizadorService cotizadorService) {
        this.cotizadorService = cotizadorService;
    }

    @PostMapping
    public ResponseEntity<CotizacionResponseDTO> cotizar(@Valid @RequestBody CotizacionRequestDTO request) {
        CotizacionResponseDTO respuesta = cotizadorService.calcularCotizacion(request);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
}