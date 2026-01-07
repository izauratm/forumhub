-- Perfis
INSERT INTO perfis (nome) VALUES ('ADMIN'), ('USER');

-- Usuários
INSERT INTO usuarios (nome, email, senha) VALUES
('Paulo', 'admin@forumhub.com', '1234'),
('Maria', 'maria@email.com', '123');

-- Relacionamento usuário-perfil
INSERT INTO usuarios_perfis (usuario_id, perfil_id) VALUES (1,1), (2,2);

-- Cursos
INSERT INTO cursos (nome, categoria) VALUES
('Spring Boot', 'Backend'),
('Java Fundamentos', 'Backend');
