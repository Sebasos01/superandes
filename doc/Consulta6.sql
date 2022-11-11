/*Consulta para gerente de sucursal */
SELECT * 
FROM (
VENTA_PRODUCTO 
)
WHERE (
        (FECHA BETWEEN to_date('03/11/2011', 'dd/mm/yyyy') AND  to_date('03/11/2015', 'dd/mm/yyyy')) 
        AND ID_SUCURSAL = 3 AND ID_USUARIO = 1
        ); --Duda para el gerente busca todas las ventas o solo las de un cliente dado
/*Consulta para gerente de supermercado */

SELECT * 
FROM (
VENTA_PRODUCTO 
)
WHERE ((FECHA BETWEEN to_date('03/11/2011', 'dd/mm/yyyy') AND  to_date('03/11/2015', 'dd/mm/yyyy'))); --Duda para el gerente busca todas las ventas o solo las de un cliente dado
/*Consulta para cliente */
SELECT * 
FROM (
VENTA_PRODUCTO 
)
WHERE ((FECHA BETWEEN to_date('03/11/2011', 'dd/mm/yyyy') AND  to_date('03/11/2015', 'dd/mm/yyyy'))
        AND ID_CLIENTE = 123); 