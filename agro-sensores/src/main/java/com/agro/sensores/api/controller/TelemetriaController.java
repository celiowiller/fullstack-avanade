package com.agro.sensores.api.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.agro.sensores.api.dto.request.LeituraRequestDTO;
import com.agro.sensores.application.usecase.RegistrarLeituraUseCase;

// Controller responsável pela telemetria
@RestController
@RequestMapping("/telemetria")
@RequiredArgsConstructor
public class TelemetriaController {

    private final RegistrarLeituraUseCase useCase;

    // Endpoint de registro de leitura
    @PostMapping
    public ResponseEntity<Void> registrar(@RequestBody @Valid LeituraRequestDTO dto) {

        // Executa caso de uso
        useCase.executar(dto.sensorId(), dto.valor(), dto.dataHora());

        // Retorna sucesso
        return ResponseEntity.status(201).build();
    }
}
