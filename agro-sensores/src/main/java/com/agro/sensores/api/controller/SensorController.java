package com.agro.sensores.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.agro.sensores.api.dto.CriarSensorDTO;
import com.agro.sensores.api.dto.AtualizarSensorDTO;
import com.agro.sensores.api.dto.AtualizarLocalizacaoDTO;
import com.agro.sensores.api.dto.SensorComLeiturasDTO;
import com.agro.sensores.api.dto.response.SensorResponseDTO;
import com.agro.sensores.application.usecase.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/sensores")
@RequiredArgsConstructor
public class SensorController {

    // varias DIs
    private final CadastrarSensorUseCase cadastrarSensorUseCase;
    private final AtualizarSensorUseCase atualizarUseCase;
    private final ListarSensoresUseCase listarUseCase;
    private final ListarSensoresComLeiturasUseCase listarComLeiturasUseCase;
    private final AtualizarLocalizacaoSensorUseCase atualizarLocalizacaoUseCase;

    // 1️. CRIAR SENSOR (ADMIN) - para criar um sensor temos de ter permissão de ADMIN
    // este contexto é determinado pela annotation @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> criar(@RequestBody @Valid CriarSensorDTO dto) {
        cadastrarSensorUseCase.executar(dto);
        return ResponseEntity.status(201).build();
    }

    // 2️. ATUALIZAR SENSOR (ADMIN)
    // no swagger, vemos o seguinte endpoint: sensores/{id}
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> atualizar(
            @PathVariable String id,
            @RequestBody @Valid AtualizarSensorDTO dto) {

        atualizarUseCase.executar(id, dto.nome());
        return ResponseEntity.ok().build();
    }

    // 3️. LISTAR TODOS OS SENSORES
    @GetMapping
    public ResponseEntity<List<SensorResponseDTO>> listar() {
        return ResponseEntity.ok(listarUseCase.executar());
    }

    // 4️. LISTAR SENSORES COM LEITURAS
    @GetMapping("/com-leituras")
    public ResponseEntity<List<SensorComLeiturasDTO>> listarComLeituras() {
        return ResponseEntity.ok(listarComLeiturasUseCase.executar());
    }

    // 5️. BUSCAR SENSOR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<SensorResponseDTO> buscarPorId(@PathVariable String id) {
        return ResponseEntity.ok(listarUseCase.buscarPorId(id));
    }

    // 6️. DELETAR SENSOR (ADMIN)
    @DeleteMapping("/{id}")
    // sensores/{id}
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        listarUseCase.deletar(id);
        return ResponseEntity.noContent().build();
    }
    
    // 7️. ATUALIZAR LOCALIZAÇÃO (ADMIN)
    @PutMapping("/{id}/localizacao")
    @PreAuthorize("hasRole('ADMIN')")
    // sensores/{id}/localizacao
    public ResponseEntity<Void> atualizarLocalizacao(
            @PathVariable String id,
            @RequestBody @Valid AtualizarLocalizacaoDTO dto) {

        atualizarLocalizacaoUseCase.executar(id, dto.localizacao());
        return ResponseEntity.ok().build();
    }
}