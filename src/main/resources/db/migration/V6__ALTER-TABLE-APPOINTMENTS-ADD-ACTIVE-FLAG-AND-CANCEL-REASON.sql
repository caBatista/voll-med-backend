ALTER TABLE appointments
    ADD COLUMN active BOOLEAN DEFAULT TRUE,
    ADD COLUMN cancel_reason VARCHAR(255);

UPDATE appointments SET active = TRUE;