CREATE TABLE doctors (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    phone_number VARCHAR(255),
    crm VARCHAR(255),
    specialty VARCHAR(255),
    street VARCHAR(255),
    zip_code VARCHAR(255),
    number INT,
    complement VARCHAR(255),
    city VARCHAR(255),
    state VARCHAR(255),
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);