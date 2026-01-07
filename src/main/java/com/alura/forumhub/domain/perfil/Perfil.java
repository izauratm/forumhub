package com.alura.forumhub.domain.perfil;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "perfis")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
}