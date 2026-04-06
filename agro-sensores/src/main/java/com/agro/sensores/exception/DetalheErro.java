package com.agro.sensores.exception;

import java.time.LocalDateTime;

//DTO padrão para retorno de erro da API
public record DetalheErro(

     // Código do erro (ex: NOT_FOUND, BUSINESS_ERROR)
     String codigo,

     // Mensagem descritiva
     String mensagem,

     // Timestamp do erro
     LocalDateTime timestamp
) {}
