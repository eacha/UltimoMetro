-- Creación tablas android
CREATE TABLE android_metadata (locale TEXT DEFAULT 'en_US');
-- Insertar datos
INSERT INTO android_metadata VALUES ('en_US');

-- Creación de las tablas
CREATE TABLE linea
( id INTEGER PRIMARY KEY NOT NULL,
  name TEXT NOT NULL,
  color TEXT NOT NULL,
  start TEXT NOT NULL,
  end  TEXT NOT NULL,
  express BOOLEAN NOT NULL );

CREATE TABLE estacion
( id INTEGER PRIMARY KEY NOT NULL,
  name TEXT NOT NULL,
  linea INTEGER NOT NULL REFERENCES LINEA(id),
  position TEXT);

CREATE TABLE horario
( id INTEGER PRIMARY KEY NOT NULL,
  estacion INTEGER NOT NULL REFERENCES ESTACION(id),
  type INTEGER NOT NULL,
  open INTEGER,
  close INTEGER,
  first_start INTEGER,
  last_start INTEGER,
  first_end INTEGER,
  last_end INTEGER);

CREATE TABLE tarifa
( id INTEGER PRIMARY KEY NOT NULL,
  name TEXT NOT NULL,
  normal TEXT NOT NULL,
  estudiante TEXT NOT NULL,
  adulto_mayor TEXT NOT NULL);

CREATE TABLE version
(
  id INTEGER PRIMARY KEY NOT NULL,
  number_version INTEGER NOT NULL
);


