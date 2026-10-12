package com.edu.utez.cotizacion.service;

import com.edu.utez.cotizacion.dto.EnvioRequestDTO;
import com.edu.utez.cotizacion.dto.EnvioResponseDTO;
import com.edu.utez.cotizacion.exception.EnvioNoPermitidoException;
import org.springframework.stereotype.Service;

@Service
public class EnvioService {

    public EnvioResponseDTO calcularEnvio(EnvioRequestDTO request) {
        String tipo = request.getTipoEnvio().toUpperCase();

        double volumen = request.getLargoCm() * request.getAnchoCm() * request.getAltoCm();

        if (request.getPesoKg() > 50) {
            throw new EnvioNoPermitidoException("El peso no puede superar los 50 kg.");
        }
        if (request.getLargoCm() > 150 || request.getAnchoCm() > 150 || request.getAltoCm() > 150) {
            throw new EnvioNoPermitidoException("Ninguna dimensión puede superar los 150 cm.");
        }
        if (volumen > 1000000) {
            throw new EnvioNoPermitidoException("El volumen no puede superar 1,000,000 cm3.");
        }

        double costoBase = 80.0;
        double costoPorPeso = request.getPesoKg() * 12.0;

        double recargoVolumen = 0.0;
        if (volumen > 50000) {
            recargoVolumen = 100.0;
        }

        double costoAcumulado = costoBase + costoPorPeso + recargoVolumen;

        double recargoTipoEnvio = 0.0;
        if (tipo.equals("EXPRESS")) {
            recargoTipoEnvio = costoAcumulado * 0.40;
        } else if (tipo.equals("MISMO_DIA")) {
            recargoTipoEnvio = costoAcumulado * 0.70;
        } else if (!tipo.equals("ESTANDAR")) {
            throw new EnvioNoPermitidoException("Tipo de envío no válido.");
        }

        double costoConTipoEnvio = costoAcumulado + recargoTipoEnvio;

        double costoSeguro = 0.0;
        if (request.getValorDeclarado() > 10000) {
            costoSeguro = request.getValorDeclarado() * 0.02;
        }

        double totalPagar = costoConTipoEnvio + costoSeguro;

        return new EnvioResponseDTO(
                request.getCodigoPostal(),
                tipo,
                redondear(volumen),
                redondear(costoBase),
                redondear(costoPorPeso),
                redondear(recargoVolumen),
                redondear(recargoTipoEnvio),
                redondear(costoSeguro),
                redondear(totalPagar)
        );
    }

    private double redondear(double valor) {
        return Math.round(valor * 100.0) / 100.0;
    }
}