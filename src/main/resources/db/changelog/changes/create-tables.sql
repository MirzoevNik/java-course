--liquibase formatted sql
--changeset mirzoevnik:create-table-mark
CREATE TABLE mark (
  id SERIAL PRIMARY KEY,
  mark_code VARCHAR(50) NOT NULL
);

--changeset mirzoevnik:create-table-model
CREATE TABLE model (
  id SERIAL PRIMARY KEY,
  mark_id INTEGER REFERENCES mark(id),
  model_code VARCHAR(50) NOT NULL
);

--changeset mirzoevnik:create-table-garage
CREATE TABLE garage (
  id SERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL
);

--changeset mirzoevnik:create-table-car
CREATE TABLE car (
  id SERIAL PRIMARY KEY,
  model_id INTEGER REFERENCES model(id),
  garage_id INTEGER REFERENCES garage(id),
  number VARCHAR(6) NOT NULL
);

