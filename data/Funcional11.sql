INSERT INTO  PEDIDO (NUM_PEDIDO, ID_PRODUCTO, ID_PROVEEDOR, ID_SUCURSAL, CANTIDAD_PRODUCTO, PRECIO_TOTAL, INICIO, DIAS_ENTREGA, ESTADO, LLEGADA) VALUES(1, '30r2YGO7','170.161.240.5', 3, 2, 6048, TO_DATE('17/08/2022', 'dd/mm/yyyy'), 4, 'en espera', TO_DATE('17/08/2022', 'dd/mm/yyyy'));
INSERT INTO PEDIDO (NUM_PEDIDO, ID_PRODUCTO, ID_PROVEEDOR, ID_SUCURSAL, CANTIDAD_PRODUCTO, PRECIO_TOTAL, INICIO, DIAS_ENTREGA, ESTADO, LLEGADA) VALUES (2, '74hZHYOS', '905.527.527-3', 2, 62, 2074, TO_DATE('25/11/2021', 'dd/mm/yyyy'), 30, 'entregado', TO_DATE('17/08/2022', 'dd/mm/yyyy'));
INSERT INTO PEDIDO (NUM_PEDIDO, ID_PRODUCTO, ID_PROVEEDOR, ID_SUCURSAL, CANTIDAD_PRODUCTO, PRECIO_TOTAL, INICIO, DIAS_ENTREGA, ESTADO, LLEGADA) VALUES (3, '01k35EB6', '118.233.527-2', 1, 53, 5161, TO_DATE('29/06/2022', 'dd/mm/yyyy'), 14, 'entregado',TO_DATE('17/08/2022', 'dd/mm/yyyy'));
INSERT INTO PEDIDO (NUM_PEDIDO, ID_PRODUCTO, ID_PROVEEDOR, ID_SUCURSAL, CANTIDAD_PRODUCTO, PRECIO_TOTAL, INICIO, DIAS_ENTREGA, ESTADO, LLEGADA) VALUES (4, '30r2YGO7', '905.527.527-3', 1, 73, 8272, TO_DATE('05/04/2022', 'dd/mm/yyyy'), 7, 'en espera', TO_DATE('17/08/2022', 'dd/mm/yyyy'));
INSERT INTO PEDIDO (NUM_PEDIDO, ID_PRODUCTO, ID_PROVEEDOR, ID_SUCURSAL, CANTIDAD_PRODUCTO, PRECIO_TOTAL, INICIO, DIAS_ENTREGA, ESTADO, LLEGADA) VALUES (5, '30r2YGO7', '253.215.214-9', 3, 42, 2842, TO_DATE('26/12/2021', 'dd/mm/yyyy'), 15, 'en espera', TO_DATE('17/01/2023', 'dd/mm/yyyy'));
INSERT INTO PEDIDO (NUM_PEDIDO, ID_PRODUCTO, ID_PROVEEDOR, ID_SUCURSAL, CANTIDAD_PRODUCTO, PRECIO_TOTAL, INICIO, DIAS_ENTREGA, ESTADO, LLEGADA) VALUES (6, '01k35EB6', '170.161.240.5', 1, 45, 7148, TO_DATE('01/10/2022', 'dd/mm/yyyy'), 6, 'en espera', TO_DATE('17/12/2022', 'dd/mm/yyyy'));
INSERT INTO PEDIDO (NUM_PEDIDO, ID_PRODUCTO, ID_PROVEEDOR, ID_SUCURSAL, CANTIDAD_PRODUCTO, PRECIO_TOTAL, INICIO, DIAS_ENTREGA, ESTADO, LLEGADA) VALUES (7, '30r2YGO7', '118.233.527-2', 1, 16, 333, TO_DATE('22/06/2022', 'dd/mm/yyyy'), 1, 'en espera', TO_DATE('17/08/2022', 'dd/mm/yyyy'));
INSERT INTO PEDIDO (NUM_PEDIDO, ID_PRODUCTO, ID_PROVEEDOR, ID_SUCURSAL, CANTIDAD_PRODUCTO, PRECIO_TOTAL, INICIO, DIAS_ENTREGA, ESTADO, LLEGADA) VALUES (9, '74hZHYOS', '253.215.214-9', 3, 48, 3505, TO_DATE('12/10/2022', 'dd/mm/yyyy'), 9, 'entregado', TO_DATE('17/08/2022', 'dd/mm/yyyy'));
INSERT INTO PEDIDO (NUM_PEDIDO, ID_PRODUCTO, ID_PROVEEDOR, ID_SUCURSAL, CANTIDAD_PRODUCTO, PRECIO_TOTAL, INICIO, DIAS_ENTREGA, ESTADO, LLEGADA) VALUES (8, '81z7ZHYL', '253.215.214-9', 2, 17, 6147,TO_DATE( '12/04/2022', 'dd/mm/yyyy'), 2, 'entregado', TO_DATE('17/08/2022', 'dd/mm/yyyy'));
COMMIT;

INSERT INTO PEDIDO (NUM_PEDIDO, ID_PRODUCTO, ID_PROVEEDOR, ID_SUCURSAL, CANTIDAD_PRODUCTO, PRECIO_TOTAL, INICIO, DIAS_ENTREGA, ESTADO, LLEGADA) VALUES (10, '81z7ZHYL',  '118.233.527-2', 2, 17, 6147,TO_DATE( '12/04/2022', 'dd/mm/yyyy'), 2, 'en espera', TO_DATE('17/08/2022', 'dd/mm/yyyy'));


DELETE FROM PEDIDO

Select * from pedido