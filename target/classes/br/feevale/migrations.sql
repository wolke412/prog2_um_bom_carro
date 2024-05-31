DROP TABLE countries;
DROP TABLE brands;
DROP TABLE vehicles;

CREATE TABLE IF NOT EXISTS countries (
    id SERIAL PRIMARY KEY,
    name VARCHAR(60) NOT NULL,
    sigla char(2) NOT NULL
);

CREATE TABLE IF NOT EXISTS brands (
    id SERIAL PRIMARY KEY,
    id_country INT REFERENCES countries (id) ON DELETE CASCADE,
    nome VARCHAR(60) NOT NULL
);

CREATE TABLE IF NOT EXISTS vehicles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    year smallint NOT NULL,
    km int DEFAULT(0),
    value_in_cents int DEFAULT(0),
    id_marca INT REFERENCES brands(id) ON DELETE CASCADE,

    CHECK (km > 0)
)