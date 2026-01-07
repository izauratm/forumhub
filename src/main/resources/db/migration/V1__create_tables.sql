CREATE TABLE perfis (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL
);

CREATE TABLE usuarios (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(150) NOT NULL,
  email VARCHAR(150) NOT NULL UNIQUE,
  senha VARCHAR(255) NOT NULL
);

CREATE TABLE usuarios_perfis (
  usuario_id BIGINT NOT NULL,
  perfil_id BIGINT NOT NULL,
  PRIMARY KEY (usuario_id, perfil_id),
  CONSTRAINT fk_up_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
  CONSTRAINT fk_up_perfil FOREIGN KEY (perfil_id) REFERENCES perfis(id)
);

CREATE TABLE cursos (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(150) NOT NULL,
  categoria VARCHAR(100) NOT NULL
);

CREATE TABLE topicos (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  titulo VARCHAR(200) NOT NULL,
  mensagem VARCHAR(400) NOT NULL,
  data_criacao DATETIME NOT NULL,
  status VARCHAR(30) NOT NULL,
  autor_id BIGINT NOT NULL,
  curso_id BIGINT NOT NULL,
  CONSTRAINT fk_topico_autor FOREIGN KEY (autor_id) REFERENCES usuarios(id),
  CONSTRAINT fk_topico_curso FOREIGN KEY (curso_id) REFERENCES cursos(id)
);

CREATE TABLE respostas (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  mensagem TEXT NOT NULL,
  topico_id BIGINT NOT NULL,
  data_criacao DATETIME NOT NULL,
  autor_id BIGINT NOT NULL,
  solucao BOOLEAN NOT NULL DEFAULT FALSE,
  CONSTRAINT fk_resposta_topico FOREIGN KEY (topico_id) REFERENCES topicos(id),
  CONSTRAINT fk_resposta_autor FOREIGN KEY (autor_id) REFERENCES usuarios(id)
);
