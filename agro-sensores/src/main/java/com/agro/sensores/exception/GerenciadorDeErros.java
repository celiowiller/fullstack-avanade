package com.agro.sensores.exception;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;  // ← Import para o logger do Lombok
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.agro.sensores.domain.exception.RecursoNaoEncontradoException;
import com.agro.sensores.domain.exception.RegraNegocioException;

import java.time.LocalDateTime;
import java.util.List;

// @Slf4j gera automaticamente um campo 'log' do tipo Logger
@Slf4j
@RestControllerAdvice
public class GerenciadorDeErros {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<DetalheErro> tratar404(RecursoNaoEncontradoException ex) {
        DetalheErro erro = new DetalheErro("NOT_FOUND", ex.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(404).body(erro);
    }

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<DetalheErro> tratarRegra(RegraNegocioException ex) {
        DetalheErro erro = new DetalheErro("BUSINESS_ERROR", ex.getMessage(), LocalDateTime.now());
        return ResponseEntity.badRequest().body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErroValidacao>> tratarValidacao(MethodArgumentNotValidException ex) {
        List<ErroValidacao> erros = ex.getFieldErrors()
                .stream()
                .map(ErroValidacao::new)
                .toList();
        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<DetalheErro> tratarConstraint(ConstraintViolationException ex) {
        DetalheErro erro = new DetalheErro("VALIDATION_ERROR", ex.getMessage(), LocalDateTime.now());
        return ResponseEntity.badRequest().body(erro);
    }

    // MÉTODO CORRIGIDO: Log simples e compatível com Spring Boot 3.4.x
    @ExceptionHandler(Exception.class)
    public ResponseEntity<DetalheErro> tratarGeral(Exception ex) {
        
        // Loga o erro COMPLETO no console (incluindo stack trace)
        log.error("ERRO INTERNO CAPTURADO: {}", ex.getMessage(), ex);
        
        // Resposta genérica para o cliente (mantém segurança)
        return ResponseEntity.status(500)
            .body(new DetalheErro("INTERNAL_ERROR", "Erro interno inesperado", LocalDateTime.now()));
    }

    private record ErroValidacao(String campo, String mensagem) {
        public ErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}