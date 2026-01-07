package com.alura.forumhub.domain.topico;

import com.alura.forumhub.domain.curso.Curso;
import com.alura.forumhub.domain.topico.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    boolean existsByTituloAndMensagem(String titulo, String mensagem);

    Page<Topico> findByCursoNomeContainingIgnoreCase(String nomeCurso, Pageable pageable);

    Page<Topico> findByCursoNomeContainingIgnoreCaseAndDataCriacaoBetween(
            String nomeCurso, LocalDateTime inicio, LocalDateTime fim, Pageable pageable);

    Optional<Topico> findById(Long id);
}

