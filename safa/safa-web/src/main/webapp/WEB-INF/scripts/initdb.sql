DELETE FROM ROL;
INSERT INTO ROL (rolId, name) VALUES (1, 'ROLE_USER');
INSERT INTO ROL (rolId, name) VALUES (2, 'ROLE_ADMIN');

DELETE FROM TAG_TYPE;
INSERT INTO TAG_TYPE (tagName, tagDataType) VALUES ('Universidad', 'STRING');
INSERT INTO TAG_TYPE (tagName, tagDataType) VALUES ('Facultad', 'STRING');
INSERT INTO TAG_TYPE (tagName, tagDataType) VALUES ('Materia', 'STRING');
INSERT INTO TAG_TYPE (tagName, tagDataType) VALUES ('Profesor', 'STRING');
INSERT INTO TAG_TYPE (tagName, tagDataType) VALUES ('Carrera', 'STRING');
INSERT INTO TAG_TYPE (tagName, tagDataType) VALUES ('Fecha de Examen', 'DATE');
INSERT INTO TAG_TYPE (tagName, tagDataType) VALUES ('Fecha de Subida', 'DATE');
INSERT INTO TAG_TYPE (tagName, tagDataType) VALUES ('Algun Tag Numerico', 'NUMERIC');