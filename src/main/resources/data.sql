DROP TABLE IF EXISTS region;

CREATE TABLE region (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  short_name VARCHAR(100) NOT NULL,
  full_name VARCHAR(100) NOT NULL,
);

INSERT INTO region (short_name, full_name) VALUES
  ('Алтай', 'Алтайский край'),
  ('Адыгея', 'Республика адыгея');