drop database if exists EVENTOS_SPRING_BOOT_UNIR_2024;
CREATE DATABASE EVENTOS_SPRING_BOOT_UNIR_2024;
USE EVENTOS_SPRING_BOOT_UNIR_2024;

CREATE TABLE TIPOS
(ID_TIPO INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
NOMBRE VARCHAR(45) NOT NULL,
DESCRIPCION VARCHAR(200)
);

CREATE TABLE EVENTOS
(ID_EVENTO INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
NOMBRE VARCHAR(50) NOT NULL,
DESCRIPCION VARCHAR(200),
FECHA_INICIO DATE,
DURACION INT,
DIRECCION VARCHAR(100),
ESTADO VARCHAR(20),
DESTACADO CHAR(1),
AFORO_MAXIMO INT,
MINIMO_ASISTENCIA INT,
PRECIO DEC(9,2),
ID_TIPO INT NOT NULL,
FOREIGN KEY(ID_TIPO) REFERENCES TIPOS(ID_TIPO)
);

CREATE TABLE USUARIOS
(
USERNAME VARCHAR(45) NOT NULL PRIMARY KEY,
PASSWORD VARCHAR(200) NOT NULL,
NOMBRE VARCHAR(50),
APELLIDOS VARCHAR(50),
DIRECCION VARCHAR(100),
ENABLED INT NOT NULL DEFAULT 1,
FECHA_REGISTRO DATE
);

INSERT INTO `tipos` (`NOMBRE`,`DESCRIPCION`) VALUES ('concierto','descripcion1');
INSERT INTO `tipos` (`NOMBRE`,`DESCRIPCION`) VALUES ('despedida','descripcion2');
INSERT INTO `tipos` (`NOMBRE`,`DESCRIPCION`) VALUES ('cumpleaños','descripcion3');
INSERT INTO `tipos` (`NOMBRE`,`DESCRIPCION`) VALUES ('boda','descripcion4');

INSERT INTO `usuarios` (`USERNAME`,`PASSWORD`,`NOMBRE`,`apellidos`,`DIRECCION`,`ENABLED`,`FECHA_REGISTRO`) VALUES ('amalia','{noop}amalia','amalia','sanchez','sevilla',1,'2021-06-01');
INSERT INTO `usuarios` (`USERNAME`,`PASSWORD`,`NOMBRE`,`apellidos`,`DIRECCION`,`ENABLED`,`FECHA_REGISTRO`) VALUES ('cacaito@fp.com','cacaito','cacaito','lopez','madrid',1,'2021-06-01');
INSERT INTO `usuarios` (`USERNAME`,`PASSWORD`,`NOMBRE`,`apellidos`,`DIRECCION`,`ENABLED`,`FECHA_REGISTRO`) VALUES ('eva@fp.com','{noop}evita','eva','perez','calle jazmin 20, sevilla',1,'2021-02-01');
INSERT INTO `usuarios` (`USERNAME`,`PASSWORD`,`NOMBRE`,`apellidos`,`DIRECCION`,`ENABLED`,`FECHA_REGISTRO`) VALUES ('javier@fp.com','{noop}javierito','javier','perez','Madrid',1,'2021-06-01');
INSERT INTO `usuarios` (`USERNAME`,`PASSWORD`,`NOMBRE`,`apellidos`,`DIRECCION`,`ENABLED`,`FECHA_REGISTRO`) VALUES ('ramon@fp.com','{noop}ramoncin','ramon','chu','Madrid',1,'2021-06-01');
INSERT INTO `usuarios` (`USERNAME`,`PASSWORD`,`NOMBRE`,`apellidos`,`DIRECCION`,`ENABLED`,`FECHA_REGISTRO`) VALUES ('ricardo@fp.com','{noop}ricardito','ricardo','moreno','Cadiz',1,'2021-06-01');
INSERT INTO `usuarios` (`USERNAME`,`PASSWORD`,`NOMBRE`,`apellidos`,`DIRECCION`,`ENABLED`,`FECHA_REGISTRO`) VALUES ('sara@fp.com','{noop}sarita','sara','martinez','calle rosal 10, madrid',1,'2021-03-01');
INSERT INTO `usuarios` (`USERNAME`,`PASSWORD`,`NOMBRE`,`apellidos`,`DIRECCION`,`ENABLED`,`FECHA_REGISTRO`) VALUES ('tomas@fp.com','{noop}tomasin','tomas','escudero','calle alamin 30, madrid',1,'2021-01-01');

CREATE TABLE RESERVAS
(ID_RESERVA INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
ID_EVENTO INT NOT NULL,
USERNAME VARCHAR(45) NOT NULL,
PRECIO_VENTA DEC(9,2),
OBSERVACIONES VARCHAR(200),
CANTIDAD INT,
FOREIGN KEY(USERNAME) REFERENCES USUARIOS(USERNAME),
FOREIGN KEY(ID_EVENTO) REFERENCES EVENTOS(ID_EVENTO)
);

-- TABLAS PARA LOGIN, LOGOUT y REGISTRO o  SPRING SECURITY 

CREATE TABLE PERFILES
(ID_PERFIL INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
NOMBRE VARCHAR(45) NOT NULL
);
INSERT INTO `perfiles` (`ID_PERFIL`,`NOMBRE`) VALUES (1,'ROLE_ADMINISTRADOR');
INSERT INTO `perfiles` (`ID_PERFIL`,`NOMBRE`) VALUES (2,'ROLE_GESTOR');
INSERT INTO `perfiles` (`ID_PERFIL`,`NOMBRE`) VALUES (3,'ROLE_CLIENTE');

CREATE TABLE USUARIO_PERFILES
(USERNAME VARCHAR(45) NOT NULL,
ID_PERFIL INT NOT NULL,
PRIMARY KEY(USERNAME, ID_PERFIL),
FOREIGN KEY(USERNAME) REFERENCES USUARIOS(USERNAME),
FOREIGN KEY(ID_PERFIL) REFERENCES PERFILES(ID_PERFIL)
);

INSERT INTO `usuario_perfiles` (`USERNAME`,`ID_PERFIL`) VALUES ('amalia',1);
INSERT INTO `usuario_perfiles` (`USERNAME`,`ID_PERFIL`) VALUES ('cacaito@fp.com',1);
INSERT INTO `usuario_perfiles` (`USERNAME`,`ID_PERFIL`) VALUES ('javier@fp.com',1);
INSERT INTO `usuario_perfiles` (`USERNAME`,`ID_PERFIL`) VALUES ('ramon@fp.com',1);
INSERT INTO `usuario_perfiles` (`USERNAME`,`ID_PERFIL`) VALUES ('ricardo@fp.com',1);
INSERT INTO `usuario_perfiles` (`USERNAME`,`ID_PERFIL`) VALUES ('tomas@fp.com',1);
INSERT INTO `usuario_perfiles` (`USERNAME`,`ID_PERFIL`) VALUES ('cacaito@fp.com',2);
INSERT INTO `usuario_perfiles` (`USERNAME`,`ID_PERFIL`) VALUES ('eva@fp.com',2);
INSERT INTO `usuario_perfiles` (`USERNAME`,`ID_PERFIL`) VALUES ('javier@fp.com',2);
INSERT INTO `usuario_perfiles` (`USERNAME`,`ID_PERFIL`) VALUES ('ricardo@fp.com',2);
INSERT INTO `usuario_perfiles` (`USERNAME`,`ID_PERFIL`) VALUES ('tomas@fp.com',2);
INSERT INTO `usuario_perfiles` (`USERNAME`,`ID_PERFIL`) VALUES ('sara@fp.com',3);
-- creacion de usuarios para aislarlo del root

INSERT INTO eventos VALUES (1, "Concierto Apashe", "músico belga", "2024-02-14", 150, "sala La Paqui (antigua Sala But), Madrid", "ACTIVO", 'N', 1000, 200, 28.00, 1);
INSERT INTO eventos VALUES (2, "Concierto de Marlena", "Dúo femenino español", "2024-02-15", 180, "sala la Riviera, Madrid", "ACTIVO", 'S', 2000, 200, 20.00, 1);
INSERT INTO eventos VALUES (3, "Concierto de Luis Fonsi", "Cantante de Puerto Rico", "2024-02-16", 180, "Palacio de los Deportes de Madrid, Madrid", "ACTIVO", 'S', 15000, 8000, 49.50, 1);
INSERT INTO eventos VALUES (4, "Concierto de Manolo García", "cantante español (El último de la fila)", "2024-02-17", 180, "Pabellón Principe Felipe, Zaragoza", "ACTIVO", 'S', 14000, 7000, 60.50, 1);
INSERT INTO eventos VALUES (5, "Concierto de Ara Malikian", "violinista libanés", "2024-02-17", 180, "Auditorio Mar de Vigo, Vigo", "CANCELADO", 'S', 1421, 600, 48.50, 1);
INSERT INTO eventos VALUES (6, "Despedida de Modric", "Jugador del Real Madrid", '2024-06-30', 180, "restaurante Zuma, Madrid", "ACTIVO", 'S', 600, 60, 199.99, 2);
INSERT INTO eventos VALUES (7, "Despedida de Joao Félix", "Jugador del FC Barcelona", '2024-06-30', 180, "Barcelona", "ACTIVO", 'S', 200, 60, 199.99, 2);
INSERT INTO eventos VALUES (8, "Cumpleaños Griezmann", "jugador del Atlético de Madrid", "2024-03-21", 200, "Sala Mala-Saña, Madrid", "ACTIVO", 'S', 500, 80, 79.99, 3);
INSERT INTO eventos VALUES (9, "Cumpleaños Alaska", "cantante hispanomexicana", "2024-06-13", 200, "Hotel Palace, Madrid", "ACTIVO", 'N', 100, 80, 59.99, 3);
INSERT INTO eventos VALUES (10, "Boda de Víctor Elías y Ana Guerra", "músicos", "2024-03-03", 180, "Lugar desconocido", "ACTIVO", 'S', 8, 7, 299.99, 4);
INSERT INTO eventos VALUES (11, "Boda de José Luis Martínez-Almeida", "Alcalde de Madrid se casa con Teresa Urquijo", "2024-04-06", 180, "Madrid", "ACTIVO", 'S', 1000, 600, 350.00, 4);
INSERT INTO eventos VALUES (12, "Boda de Natalia Jimenez", "se casa con Arnold Hemkes", "2024-03-01", 180, "Lugar desconocido", "ACTIVO", 'S', 1000, 600, 499.99, 4);
INSERT INTO eventos VALUES (13, "Boda de Gerard Piqué", "ex-futbolista del FC Barcelona se casa con Clara Chía", "2024-03-01", 150, "Barcelona", "CANCELADO", 'S', 800, 500, 799.99, 4);

INSERT INTO reservas VALUES (1, 2, "sara@fp.com", 20.00, "Sin observaciones", 5);
INSERT INTO reservas VALUES (2, 2, "sara@fp.com", 20.00, "Sin observaciones", 3);
INSERT INTO reservas VALUES (3, 11, "sara@fp.com", 350.00, "Sin observaciones", 3);

DROP USER ureservas_unir;
create user ureservas_unir identified by 'ureservas';
grant all privileges on EVENTOS_SPRING_BOOT_UNIR_2024.* to ureservas_unir;
