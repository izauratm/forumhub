package com.alura.forumhub.controller;

import com.alura.forumhub.dto.*;
import com.alura.forumhub.service.TopicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name="bearer-key")
@Tag(name = "Operações realizadas para os Tópicos:", description = """
    - Criar tópicos
    - Detalhar os tópicos
    - Atualizar os tópicos
    - Deletar tópicos (somente ADMIN)
""")

public class TopicoController {

    private final TopicoService service;

    public TopicoController(TopicoService service) {
        this.service = service;
    }

    @PostMapping
    @Transactional
    @Operation(summary = "Criar um novo tópico")
    public ResponseEntity<TopicoResponse> criar(@RequestBody @Valid TopicoRequest request) {
        TopicoResponse resp = service.criar(request);
        return ResponseEntity.ok(resp);
    }

    @GetMapping
    @Operation(summary = "Listar tópicos")
    public ResponseEntity<Page<TopicoResponse>> listar(
            @PageableDefault(size = 10, sort = "dataCriacao") Pageable pageable,
            @RequestParam(required = false) String curso,
            @RequestParam(required = false) Integer ano) {

        Page<TopicoResponse> page;
        if (curso != null && ano != null) {
            page = service.listarPorCursoEAno(curso, ano, pageable);
        } else {
            page = service.listar(pageable);
        }
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Detalhar um tópico pelo ID")
    public ResponseEntity<TopicoResponse> detalhar(@PathVariable Long id) {
        return ResponseEntity.ok(service.detalhar(id));
    }

    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Atualizar um tópico existente")
    public ResponseEntity<TopicoResponse> atualizar(@PathVariable Long id,
                                                    @RequestBody @Valid TopicoUpdateRequest request) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Deletar um tópico (somente ADMIN)")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
