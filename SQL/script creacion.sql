DROP DATABASE IF EXISTS `TACS-UTN`;

CREATE DATABASE `TACS-UTN`;

USE `TACS-UTN`;

-- =====================================================================
-- CREACION DE TABLAS
-- =====================================================================

CREATE TABLE TIPO_DOCUMENTO (
	id	 		INTEGER NOT NULL,
	descripcion	VARCHAR(255),

	CONSTRAINT PK_TIPO_DOCUMENTO PRIMARY KEY (id)
);

CREATE TABLE USUARIO (
	id		 			INTEGER 		NOT NULL 	AUTO_INCREMENT,
	nick 				VARCHAR(255)	NOT NULL,
	pass 				VARCHAR(255)	NOT NULL,
	intentos_login 		SMALLINT		NOT NULL,
	activo 				bit,
	nombre 				varchar(255),
	apellido 			varchar(255),
	tipo_documento 		INTEGER,
	documento 			VARCHAR(12),
	fecha_nacimiento	date,
	direccion 			VARCHAR(255),
	telefono 			VARCHAR(255),
	email 				VARCHAR(255),
	sexo 				ENUM('M','F','O'),
	
	CONSTRAINT PK_USUARIO			PRIMARY KEY (id),
	CONSTRAINT FK_TIPO_DOCUMENTO 	FOREIGN KEY (tipo_documento) REFERENCES TIPO_DOCUMENTO(id)
);

-- =====================================================================
-- FUNCION HASH Y TRIGGER PARA LA CONTRASEÑA
-- =====================================================================

DROP FUNCTION IF EXISTS fn_hashear_pass;

CREATE FUNCTION fn_hashear_pass(pass VARCHAR(255)) 
RETURNS VARCHAR(255)
BEGIN
	RETURN SHA2(pass, 256);
END;

-- pasar trigger a mysql
-- CREATE TRIGGER tg_hashear_pass_insert  
-- ON USUARIO  
-- instead of insert  
-- as begin  
--     insert into dbo.USUARIO  
--     select
--       nick,  
--       dbo.fn_hashear_pass(pass),  
--       intentos_login,
--       activo,
--       nombre, 
--       apellido,
--       tipo_documento,
--       documento,
--       fecha_nacimiento,  
--       direccion, 
--       telefono,
--       mail,  
--       sexo
--     from inserted   
-- end  
   
-- =====================================================================
-- DATOS DE PRUEBA
-- =====================================================================

INSERT INTO TIPO_DOCUMENTO 
	(id, descripcion) 
VALUES 
	(1,'DNI');

INSERT INTO USUARIO 
	(nick,pass,intentos_login,activo,nombre,apellido,tipo_documento,documento,fecha_nacimiento,direccion,telefono,email,sexo) 
VALUES 
	('1133472',fn_hashear_pass('1133472'),0,1,'DEL CIELO','ARCE',1,'1133472','1908-05-12','AVENIDA LUIS MARÍA CAMPOS 1499','84370394','cielo_Arce@gmail.com',NULL),
	('1300319',fn_hashear_pass('1300319'),0,1,'IOSEF','MUÑOZ',1,'1300319','1946-07-24','AVENIDA PEDRO GOYENA 7579','27551875','iosef_Muñoz@gmail.com',NULL),
	('1305648',fn_hashear_pass('1305648'),0,1,'LENA','GALLARDO',1,'1305648','1916-07-20','AVENIDA ANTÁRTIDA ARGENTINA 7162','89260090','lena_Gallardo@gmail.com',NULL),
	('1320627',fn_hashear_pass('1320627'),0,1,'ILONA','ARAYA',1,'1320627','1913-11-23','AVENIDA AMANCIO ALCORTA 6026','19629364','ilona_Araya@gmail.com',NULL),
	('1330335',fn_hashear_pass('1330335'),0,1,'MARISABELA','MUÑOZ',1,'1330335','1949-04-16','AVENIDA ÁNGEL GALLARDO 3935','67507689','marisabela_Muñoz@gmail.com',NULL),
	('1344257',fn_hashear_pass('1344257'),0,1,'MARA','RAMÍREZ',1,'1344257','1941-11-05','AVENIDA ESCALADA 4764','56269923','mara_Ramírez@gmail.com',NULL),
	('1498437',fn_hashear_pass('1498437'),0,1,'ROMANA','PÉREZ',1,'1498437','1920-09-28','AVENIDA JUAN B. JUSTO 7606','71549511','romana_Pérez@gmail.com',NULL),
	('1585572',fn_hashear_pass('1585572'),0,1,'VALENTE','ÁLVAREZ',1,'1585572','1977-06-26','AVENIDA CASEROS 2311','70271188','valente_Álvarez@gmail.com',NULL),
	('1868493',fn_hashear_pass('1868493'),0,1,'DEL SOLAR','RÍOS',1,'1868493','1962-08-13','AVENIDA COSTANERA RAFAEL OBLIGADO 1048','48180308','del solar_Ríos@gmail.com',NULL),
	('1892861',fn_hashear_pass('1892861'),0,1,'FORTUNA','CÁCERES',1,'1892861','1978-02-28','AVENIDA MONROE 4096','32324266','fortuna_Cáceres@gmail.com',NULL);
	
