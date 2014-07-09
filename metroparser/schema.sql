-- Creación de las tablas
CREATE TABLE LINEA
( id INTEGER PRIMARY KEY NOT NULL,
  name TEXT NOT NULL,
  color TEXT NOT NULL,
  start TEXT NOT NULL,
  end  TEXT NOT NULL,
  express BOOLEAN NOT NULL );

CREATE TABLE ESTACION
( id INTEGER PRIMARY KEY NOT NULL,
  name TEXT NOT NULL,
  position TEXT);

