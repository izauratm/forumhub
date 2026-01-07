package com.alura.forumhub.domain.curso;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alura.forumhub.domain.curso.Curso;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
}