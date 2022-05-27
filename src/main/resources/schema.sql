DROP ALL OBJECTS; -- commande h2 pour réinitialiser la base

CREATE TABLE vin (
    id serial PRIMARY KEY,
    -- id identity PRIMARY KEY,
    chateau VARCHAR(50) NOT NULL,
    appellation VARCHAR(50),
    prix NUMERIC(10, 2)
);

CREATE SEQUENCE vin_id_seq start with 1 increment 100;
