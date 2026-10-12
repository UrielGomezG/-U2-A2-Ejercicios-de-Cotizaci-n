package com.edu.utez.cotizacion.controller;

import com.edu.utez.cotizacion.dto.EnvioRequestDTO;
import com.edu.utez.cotizacion.dto.EnvioResponseDTO;
import com.edu.utez.cotizacion.service.EnvioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/envios")
public class EnvioController {

    private final EnvioService envioService;

    public EnvioController(EnvioService envioService) {
        this.envioService = envioService;
    }

    @PostMapping
    public ResponseEntity<EnvioResponseDTO> cotizarEnvio(@Valid @RequestBody EnvioRequestDTO request) {
        EnvioResponseDTO respuesta = envioService.calcularEnvio(request);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
}