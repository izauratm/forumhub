-- Evitar tópicos duplicados (mesmo título + mensagem)
CREATE UNIQUE INDEX ux_topicos_titulo_mensagem ON topicos (titulo, mensagem);

-- Índices úteis
CREATE INDEX ix_topicos_data ON topicos (data_criacao);
CREATE INDEX ix_topicos_status ON topicos (status);
CREATE INDEX ix_cursos_nome ON cursos (nome);
