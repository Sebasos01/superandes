CREATE SEQUENCE clienteSeq
  START WITH 1
  INCREMENT BY 1
  MINVALUE 1; 


INSERT INTO USUARIO(CODIGO_USUARIO, DOCUMENTO, TIPO_DOCUMENTO,NOMBRE,EMAIL, CONTRASENA,ID_TIPO,PUNTOS,DIRECCION,CIUDAD,ID_SUCURSAL )
VALUES (clienteSeq.nextval,'1036515287','TI', 'Juan Guzm?n','j.guz@gmail.com', 'Guzman1324',1, NULL,'Calle 56 N 78 15', 'Cali', 2); 


--Del tipo gerente sucursal 
INSERT INTO USUARIO(CODIGO_USUARIO, DOCUMENTO, TIPO_DOCUMENTO,NOMBRE,EMAIL, CONTRASENA,ID_TIPO,PUNTOS,DIRECCION,CIUDAD,ID_SUCURSAL )
VALUES (clienteSeq.nextval,'16253418', 'CC', 'Luisa Gutierrez','lulu123@gmail.com', 'lulu1111',2,NULL,'Calle 45 N 78 15', 'Bogota', 2); 

--Del tipo gerente supermercado
INSERT INTO USUARIO(CODIGO_USUARIO, DOCUMENTO, TIPO_DOCUMENTO,NOMBRE,EMAIL, CONTRASENA,ID_TIPO,PUNTOS,DIRECCION,CIUDAD,ID_SUCURSAL )
VALUES (clienteSeq.nextval,'45362716', 'CE', 'Jean Sartre','JeanPaul@gmail.com', 'OuiOui123',3,NULL,'Calle 10 N 78 15', 'Medellin', 2); 


--Del tipo operador
INSERT INTO USUARIO(CODIGO_USUARIO, DOCUMENTO, TIPO_DOCUMENTO,NOMBRE,EMAIL, CONTRASENA,ID_TIPO,PUNTOS,DIRECCION,CIUDAD,ID_SUCURSAL )
VALUES (clienteSeq.nextval,'25482716', 'CC', 'Santiago Mesa','santiagoM@gmail.com', 'contrasena1212',4,NULL,'Calle 101 N 78 15', 'Cali', 2); 


--Del tipo cliente
INSERT INTO USUARIO(CODIGO_USUARIO, DOCUMENTO, TIPO_DOCUMENTO,NOMBRE,EMAIL, CONTRASENA,ID_TIPO,PUNTOS,DIRECCION,CIUDAD,ID_SUCURSAL )
VALUES (clienteSeq.nextval,'25482716', 'CC', 'Santiago Mesa','santiagoM@gmail.com', 'contrasena1212',5,NULL,'Calle 59 N 78 15', 'Cali', 2); 


SELECT * 
FROM USUARIO; 