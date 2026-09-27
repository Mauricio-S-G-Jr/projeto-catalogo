CREATE TABLE IF NOT EXISTS catalog_item (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(150) NOT NULL,
    author_director VARCHAR(150) NOT NULL,
    publication_year INT NOT NULL,
    genre VARCHAR(100) NOT NULL,
    synopsis TEXT,
    PRIMARY KEY (id)
);

INSERT INTO catalog_item (title, author_director, publication_year, genre, synopsis)
VALUES
    ('O Senhor dos Anéis', 'J.R.R. Tolkien', 1954, 'Fantasia', 'Uma jornada épica para destruir o Um Anel.'),
    ('Matrix', 'Lana e Lilly Wachowski', 1999, 'Ficção Científica', 'Um hacker descobre a verdade sobre a realidade.'),
    ('Dom Casmurro', 'Machado de Assis', 1899, 'Romance', 'Um clássico sobre ciúmes, memória e amor.')
ON DUPLICATE KEY UPDATE
    title = VALUES(title),
    author_director = VALUES(author_director),
    publication_year = VALUES(publication_year),
    genre = VALUES(genre),
    synopsis = VALUES(synopsis);
