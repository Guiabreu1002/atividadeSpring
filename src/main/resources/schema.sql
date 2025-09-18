CREATE TABLE IF NOT EXISTS livro (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    titulo VARCHAR(255),
    autor VARCHAR(100),
    ano INT,
    genero VARCHAR(100),
    editora VARCHAR(100),
    numero_paginas INT
);

