CREATE TABLE comentario (
    id INT PRIMARY KEY,
    texto TEXT NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    postagem_id INT NOT NULL,
    CONSTRAINT fk_comentario_postagem
        FOREIGN KEY (postagem_id)
        REFERENCES postagem (id)
);
