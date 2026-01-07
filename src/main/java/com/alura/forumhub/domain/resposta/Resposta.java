package com.alura.forumhub.domain.resposta;

import com.alura.forumhub.domain.topico.Topico;
import com.alura.forumhub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity @Table(name="respostas")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Resposta {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, columnDefinition="TEXT")
    private String mensagem;

    @ManyToOne(optional=false)
    @JoinColumn(name="topico_id")
    private Topico topico;

    @Column(name="data_criacao", nullable=false)
    private LocalDateTime dataCriacao;

    @ManyToOne(optional=false)
    @JoinColumn(name="autor_id")
    private Usuario autor;

    @Column(nullable=false)
    private boolean solucao = false;
}
