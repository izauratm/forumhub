package com.alura.forumhub.dto;

import com.alura.forumhub.domain.topico.StatusTopico;

import java.time.LocalDateTime;

public record TopicoResponse(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        StatusTopico status,
        String autorNome,
        String cursoNome
) {}
