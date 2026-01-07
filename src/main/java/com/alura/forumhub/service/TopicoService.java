package com.alura.forumhub.service;

import com.alura.forumhub.domain.curso.Curso;
import com.alura.forumhub.domain.topico.*;
import com.alura.forumhub.domain.usuario.Usuario;
import com.alura.forumhub.domain.usuario.UsuarioRepository;
import com.alura.forumhub.dto.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TopicoService {

    private final com.alura.forumhub.domain.topico.TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;
    private final org.springframework.data.jpa.repository.JpaRepository<Curso, Long> cursoRepository;

    public TopicoService(com.alura.forumhub.domain.topico.TopicoRepository topicoRepository,
                         UsuarioRepository usuarioRepository,
                         org.springframework.data.jpa.repository.JpaRepository<Curso, Long> cursoRepository) {
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
        this.cursoRepository = cursoRepository;
    }

    @Transactional
    public TopicoResponse criar(TopicoRequest request) {
        if (topicoRepository.existsByTituloAndMensagem(request.titulo(), request.mensagem())) {
            throw new IllegalArgumentException("Tópico duplicado: título e mensagem já existem.");
        }

        Usuario autor = usuarioRepository.findById(request.autorId())
                .orElseThrow(() -> new EntityNotFoundException("Autor não encontrado"));

        Curso curso = cursoRepository.findById(request.cursoId())
                .orElseThrow(() -> new EntityNotFoundException("Curso não encontrado"));

        Topico topico = new Topico();
        topico.setTitulo(request.titulo());
        topico.setMensagem(request.mensagem());
        topico.setDataCriacao(LocalDateTime.now());
        topico.setStatus(StatusTopico.ABERTO);
        topico.setAutor(autor);
        topico.setCurso(curso);

        Topico salvo = topicoRepository.save(topico);
        return toResponse(salvo);
    }

    public Page<TopicoResponse> listar(Pageable pageable) {
        return topicoRepository.findAll(pageable).map(this::toResponse);
    }

    public Page<TopicoResponse> listarPorCursoEAno(String nomeCurso, int ano, Pageable pageable) {
        LocalDateTime inicio = LocalDateTime.of(ano, 1, 1, 0, 0);
        LocalDateTime fim = LocalDateTime.of(ano, 12, 31, 23, 59);
        return topicoRepository
                .findByCursoNomeContainingIgnoreCaseAndDataCriacaoBetween(nomeCurso, inicio, fim, pageable)
                .map(this::toResponse);
    }

    public TopicoResponse detalhar(Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico não encontrado"));
        return toResponse(topico);
    }

    @Transactional
    public TopicoResponse atualizar(Long id, TopicoUpdateRequest request) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico não encontrado"));

        if (topicoRepository.existsByTituloAndMensagem(request.titulo(), request.mensagem())
                && !(topico.getTitulo().equals(request.titulo()) && topico.getMensagem().equals(request.mensagem()))) {
            throw new IllegalArgumentException("Tópico duplicado: título e mensagem já existem.");
        }

        Curso curso = cursoRepository.findById(request.cursoId())
                .orElseThrow(() -> new EntityNotFoundException("Curso não encontrado"));

        topico.setTitulo(request.titulo());
        topico.setMensagem(request.mensagem());
        topico.setCurso(curso);

        Topico atualizado = topicoRepository.save(topico);
        return toResponse(atualizado);
    }

    @Transactional
    public void excluir(Long id) {
        if (!topicoRepository.existsById(id)) {
            throw new EntityNotFoundException("Tópico não encontrado");
        }
        topicoRepository.deleteById(id);
    }

    private TopicoResponse toResponse(Topico t) {
        return new TopicoResponse(
                t.getId(),
                t.getTitulo(),
                t.getMensagem(),
                t.getDataCriacao(),
                t.getStatus(),
                t.getAutor().getNome(),
                t.getCurso().getNome()
        );
    }
}
