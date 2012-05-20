DELETE FROM TAG_CONTENT;
DELETE FROM SAFA_USER_ROLE;
DELETE FROM INDICATOR_CONTENT;
DELETE FROM ROLE;
UPDATE RESOURCE SET contentId=null WHERE resourceId=1;
UPDATE CONTENT SET idThumbnailResource=null WHERE contentId=1;
UPDATE RESOURCE SET contentId=null WHERE resourceId=3;
UPDATE CONTENT SET idThumbnailResource=null WHERE contentId=2;
DELETE FROM RESOURCE;
DELETE FROM CONTENT;
DELETE FROM SAFA_USER;
DELETE FROM TAG;
DELETE FROM TAG_TYPE;

INSERT INTO ROLE (roleId, name) VALUES (1, 'ROLE_USER');
INSERT INTO ROLE (roleId, name) VALUES (2, 'ROLE_ADMIN');


INSERT INTO TAG_TYPE (tagName, tagDataType) VALUES ('Universidad', 'STRING');
INSERT INTO TAG_TYPE (tagName, tagDataType) VALUES ('Facultad', 'STRING');
INSERT INTO TAG_TYPE (tagName, tagDataType) VALUES ('Materia', 'STRING');
INSERT INTO TAG_TYPE (tagName, tagDataType) VALUES ('Profesor', 'STRING');
INSERT INTO TAG_TYPE (tagName, tagDataType) VALUES ('Carrera', 'STRING');
INSERT INTO TAG_TYPE (tagName, tagDataType) VALUES ('Fecha de Examen', 'DATE');
INSERT INTO TAG_TYPE (tagName, tagDataType) VALUES ('Fecha de Subida', 'DATE');
INSERT INTO TAG_TYPE (tagName, tagDataType) VALUES ('Algún Tag Numérico', 'NUMERIC');
INSERT INTO TAG_TYPE (tagName, tagDataType) VALUES ('Resource Type', 'NUMERIC');

INSERT INTO TAG (tagId, tagName, value, iconURL) VALUES (1, 'Universidad', 'UTN', '');
INSERT INTO TAG (tagId, tagName, value, iconURL) VALUES (2, 'Facultad', 'FRBA', '');
INSERT INTO TAG (tagId, tagName, value, iconURL) VALUES (3, 'Facultad', 'FRC', '');
INSERT INTO TAG (tagId, tagName, value, iconURL) VALUES (10, 'Facultad', 'FRSF', '');
INSERT INTO TAG (tagId, tagName, value, iconURL) VALUES (4, 'Materia', 'Matemática Discreta', '');
INSERT INTO TAG (tagId, tagName, value, iconURL) VALUES (5, 'Materia', 'Paradigmas de Programación', '');
INSERT INTO TAG (tagId, tagName, value, iconURL) VALUES (6, 'Profesor', 'Susana Granado Peralta', '');
INSERT INTO TAG (tagId, tagName, value, iconURL) VALUES (7, 'Carrera', 'Ingeniería en Sistemas de Información', '');
INSERT INTO TAG (tagId, tagName, value, iconURL) VALUES (8, 'Resource Type', 'PDF', 'http://2.bp.blogspot.com/_Ht1_rh8kGYQ/TT790CwQs_I/AAAAAAAADao/eeNAPJZB0OE/s1600/pdf.png');
INSERT INTO TAG (tagId, tagName, value, iconURL) VALUES (9, 'Resource Type', 'Imagen', 'http://rededuca.wikispaces.com/file/view/imagen/30143250/imagen');


INSERT INTO RESOURCE (resourceId, url, resourceType, size, description, contentId) VALUES (1, 'http://www.mediafire.com/?oum7aa6t5bpscda', 8, 0, 'lalala1', null);
INSERT INTO RESOURCE (resourceId, url, resourceType, size, description, contentId) VALUES (2, 'http://www.definicionabc.com/wp-content/uploads/apuntes.jpg', 9, 0, 'Default Content Thumbnail', null);
INSERT INTO RESOURCE (resourceId, url, resourceType, size, description, contentId) VALUES (3, 'http://www.mediafire.com/?oum7aa6t5bpscda', 8, 0, 'lalala2', null);


INSERT INTO SAFA_USER (userId, urlToken, username, password, email, locked) VALUES (1, 'http://openid.net/elToken', 'LaPosta', '123456', 'reyiyo@gmail.com', false);

DELETE FROM INDICATOR_CONTENT;
INSERT INTO SAFA_USER_ROLE VALUES(1, 1);

INSERT INTO CONTENT (contentId, available, description, reviewed, idThumbnailResource, title, uploadDate, userId) VALUES (1, true, 'lalala1', true, 2, 'Resumen pedorro1', CURRENT_DATE, 1);
INSERT INTO CONTENT (contentId, title, description, uploadDate, userId, available, idThumbnailResource, reviewed) VALUES (2, 'Resumen pedorro2', 'lalala2', CURRENT_DATE, 1, true, 2, true);

UPDATE RESOURCE SET contentId=1 WHERE resourceId=1
UPDATE RESOURCE SET contentId=2 WHERE resourceId=3


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