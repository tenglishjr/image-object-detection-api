CREATE TABLE IF NOT EXISTS images (
    id SERIAL PRIMARY KEY,
    date_uploaded DATE NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    label VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS image_tags (
    id SERIAL PRIMARY KEY,
    tag VARCHAR(255) NOT NULL,
    confidence NUMERIC NOT NULL,
    tags_fkey INTEGER REFERENCES images(id)
);