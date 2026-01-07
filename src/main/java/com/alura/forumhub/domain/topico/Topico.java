package com.alura.forumhub.domain.topico;

import com.alura.forumhub.domain.curso.Curso;
import com.alura.forumhub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity @Table(name="topicos")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Topico {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=200)
    private String titulo;

    @Column(nullable=false, columnDefinition="TEXT")
    private String mensagem;

    @Column(name="data_criacao", nullable=false)
    private LocalDateTime dataCriacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false, length=30)
    private StatusTopico status;

    @ManyToOne(optional=false)
    @JoinColumn(name="autor_id")
    private Usuario autor;

    @ManyToOne(optional=false)
    @JoinColumn(name="curso_id")
    private Curso curso;
}
