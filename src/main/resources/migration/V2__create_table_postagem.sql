CREATE TABLE postagem (
                          id SERIAL PRIMARY KEY,
                          titulo VARCHAR(200) NOT NULL,
                          conteudo TEXT NOT NULL,
                          data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          usuario_id INT NOT NULL,
                          CONSTRAINT fk_postagem_usuario FOREIGN KEY (usuario_id)
                              REFERENCES usuario(id)
                              ON DELETE CASCADE
                              ON UPDATE CASCADE
);
