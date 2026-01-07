package com.alura.forumhub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicoUpdateRequest(
        @NotBlank String titulo,
        @NotBlank String mensagem,
        @NotNull Long cursoId
) {}
