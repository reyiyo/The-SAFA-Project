DELETE FROM ROLE;
DELETE FROM TAG_CONTENT;
DELETE FROM SAFA_USER_ROLE;
DELETE FROM INDICATOR_CONTENT;

UPDATE RESOURCE SET contentId=null WHERE id=1;
UPDATE CONTENT SET idThumbnailResource=null WHERE contentId=1;
UPDATE RESOURCE SET contentId=null WHERE id=3;
UPDATE CONTENT SET idThumbnailResource=null WHERE contentId=2;
DELETE FROM RESOURCE;
DELETE FROM CONTENT;
DELETE FROM SAFA_USER;
DELETE FROM TAG;
DELETE FROM TAG_TYPE;

INSERT INTO ROLE (id, name) VALUES (1, 'ROLE_USER');
INSERT INTO ROLE (id, name) VALUES (2, 'ROLE_ADMIN');


INSERT INTO TAG_TYPE (id, tagName, tagDataType) VALUES (1, 'Universidad', 'STRING');
INSERT INTO TAG_TYPE (id, tagName, tagDataType) VALUES (2, 'Facultad', 'STRING');
INSERT INTO TAG_TYPE (id, tagName, tagDataType) VALUES (3, 'Materia', 'STRING');
INSERT INTO TAG_TYPE (id, tagName, tagDataType) VALUES (4, 'Profesor', 'STRING');
INSERT INTO TAG_TYPE (id, tagName, tagDataType) VALUES (5, 'Carrera', 'STRING');
INSERT INTO TAG_TYPE (id, tagName, tagDataType) VALUES (6, 'Fecha de Examen', 'DATE');
INSERT INTO TAG_TYPE (id, tagName, tagDataType) VALUES (7, 'Fecha de Subida', 'DATE');
INSERT INTO TAG_TYPE (id, tagName, tagDataType) VALUES (8, 'Algún Tag Numérico', 'NUMERIC');
INSERT INTO TAG_TYPE (id, tagName, tagDataType) VALUES (9, 'Resource Type', 'NUMERIC');

INSERT INTO TAG (id, tagTypeId, value, iconURL) VALUES (1, 1, 'UTN', '');
INSERT INTO TAG (id, tagTypeId, value, iconURL) VALUES (2, 2, 'FRBA', '');
INSERT INTO TAG (id, tagTypeId, value, iconURL) VALUES (3, 2, 'FRC', '');
INSERT INTO TAG (id, tagTypeId, value, iconURL) VALUES (10, 2, 'FRSF', '');
INSERT INTO TAG (id, tagTypeId, value, iconURL) VALUES (4, 3, 'Matemática Discreta', '');
INSERT INTO TAG (id, tagTypeId, value, iconURL) VALUES (5, 3, 'Paradigmas de Programación', '');
INSERT INTO TAG (id, tagTypeId, value, iconURL) VALUES (6, 4, 'Susana Granado Peralta', '');
INSERT INTO TAG (id, tagTypeId, value, iconURL) VALUES (7, 5, 'Ingeniería en Sistemas de Información', '');
INSERT INTO TAG (id, tagTypeId, value, iconURL) VALUES (8, 9, 'PDF', 'http://2.bp.blogspot.com/_Ht1_rh8kGYQ/TT790CwQs_I/AAAAAAAADao/eeNAPJZB0OE/s1600/pdf.png');
INSERT INTO TAG (id, tagTypeId, value, iconURL) VALUES (9, 9, 'Imagen', 'http://rededuca.wikispaces.com/file/view/imagen/30143250/imagen');


INSERT INTO RESOURCE (id, url, resourceType, size, description, contentId) VALUES (1, 'http://www.mediafire.com/?oum7aa6t5bpscda', 8, 0, 'lalala1', null);
INSERT INTO RESOURCE (id, url, resourceType, size, description, contentId) VALUES (2, 'http://www.definicionabc.com/wp-content/uploads/apuntes.jpg', 9, 0, 'Default Content Thumbnail', null);
INSERT INTO RESOURCE (id, url, resourceType, size, description, contentId) VALUES (3, 'http://www.mediafire.com/?oum7aa6t5bpscda', 8, 0, 'lalala2', null);


INSERT INTO SAFA_USER (id, username, password, locked) VALUES (1, 'LaPosta', '123456', false);

DELETE FROM INDICATOR_CONTENT;
INSERT INTO SAFA_USER_ROLE VALUES(1, 1);

INSERT INTO CONTENT (contentId, available, description, reviewed, idThumbnailResource, title, uploadDate, userId) VALUES (1, true, 'lalala1', true, 2, 'Resumen pedorro1', CURRENT_DATE, 1);
INSERT INTO CONTENT (contentId, title, description, uploadDate, userId, available, idThumbnailResource, reviewed) VALUES (2, 'Resumen pedorro2', 'lalala2', CURRENT_DATE, 1, true, 2, true);

UPDATE RESOURCE SET contentId=1 WHERE id=1
UPDATE RESOURCE SET contentId=2 WHERE id=3


INSERT INTO TAG_CONTENT VALUES (1, 1);
INSERT INTO TAG_CONTENT VALUES (1, 2);
INSERT INTO TAG_CONTENT VALUES (1, 4);
INSERT INTO TAG_CONTENT VALUES (1, 6);
INSERT INTO TAG_CONTENT VALUES (1, 7);

INSERT INTO TAG_CONTENT VALUES (2, 1);
INSERT INTO TAG_CONTENT VALUES (2, 2);
INSERT INTO TAG_CONTENT VALUES (2, 4);
INSERT INTO TAG_CONTENT VALUES (2, 6);
INSERT INTO TAG_CONTENT VALUES (2, 7);

DELETE FROM INDICATOR;
DELETE FROM INDICATOR_TYPE;

INSERT INTO INDICATOR_TYPE (indicatorName, minValue, maxValue) VALUES ('Votos', 0, 10);
INSERT INTO INDICATOR_TYPE (indicatorName, minValue, maxValue) VALUES ('Completitud', 0, 10);
INSERT INTO INDICATOR_TYPE (indicatorName, minValue, maxValue) VALUES ('Es la posta', 0, 10);

INSERT INTO INDICATOR (indicatorId, indicatorName, value) VALUES (1, 'Votos', 1);
INSERT INTO INDICATOR (indicatorId, indicatorName, value) VALUES (2, 'Completitud', 1);
INSERT INTO INDICATOR (indicatorId, indicatorName, value) VALUES (3, 'Es la posta', 1);

INSERT INTO INDICATOR (indicatorId, indicatorName, value) VALUES (4, 'Votos', 10);
INSERT INTO INDICATOR (indicatorId, indicatorName, value) VALUES (5, 'Completitud', 10);
INSERT INTO INDICATOR (indicatorId, indicatorName, value) VALUES (6, 'Es la posta', 10);


INSERT INTO INDICATOR_CONTENT VALUES (1,1);
INSERT INTO INDICATOR_CONTENT VALUES (1,2);
INSERT INTO INDICATOR_CONTENT VALUES (1,3);

INSERT INTO INDICATOR_CONTENT VALUES (2,4);
INSERT INTO INDICATOR_CONTENT VALUES (2,5);
INSERT INTO INDICATOR_CONTENT VALUES (2,6);