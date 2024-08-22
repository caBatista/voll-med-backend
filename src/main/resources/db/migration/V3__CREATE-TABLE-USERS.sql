CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255),
    password VARCHAR(255)
);

INSERT INTO users VALUES (1, 'caio.batista', '$2a$12$iVwr7TMQkJHc11Ka1e.H5.6z82aqapdG2LJv2n.1EcM5uibXZ78Bi')