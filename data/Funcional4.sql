CREATE SEQUENCE almacenSeq
  START WITH 1
  INCREMENT BY 1
  MINVALUE 1; 
  
INSERT INTO ALMACEN(ID, CAPACIDAD_VOLUMEN, CAPACIDAD_PESO, CATEGORIA,TIPO,NIV_ABASTECIMIENTO_VOLUMEN, NIV_ABASTECIMIENTO_PESO, ID_SUCURSAL)
VALUES (almacenSeq.nextval,50000,500,'aseo','bodega', 25000,200,2 ); 
