CREATE TABLE vin (
    id serial PRIMARY KEY,
    chateau VARCHAR(50) NOT NULL,
    appellation VARCHAR(50),
    prix DECIMAL
);

CREATE SEQUENCE vin_id_seq start 1 increment 1;
