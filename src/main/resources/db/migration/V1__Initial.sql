-- noinspection SqlNoDataSourceInspectionForFile

CREATE TABLE IF NOT EXISTS images (
    id SERIAL PRIMARY KEY,
    date_uploaded DATE NOT NULL,
    label VARCHAR(255) NULL,
    tags VARCHAR(1000) NULL
);