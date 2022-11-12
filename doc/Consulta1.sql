/*
DECLARE 
    r1Date DATE := to_date('03/11/2011', 'dd/mm/yyyy');
    r2Date DATE := to_date('03/11/2015', 'dd/mm/yyyy');
    sucursalGerente INTEGER:=(3);
*/
/*Consulta para el gerente de sucursal*/
--Retorna una casilla con el dinero recolectado por esa sucursal en ese rango de fechas
--Sin promocion
SELECT  NVL(SUM( precioProducto.PRECIO_VENTA*referencia.CANTIDAD),0) AS DINERO_RECOLECTADO
    FROM
    (
        (SELECT ID_SUCURSAL,ID_PRODUCTO, CANTIDAD
        FROM (VENTA_PRODUCTO)
        WHERE ((FECHA BETWEEN to_date('15/01/2021', 'dd/mm/yyyy') AND  to_date('15/01/2022', 'dd/mm/yyyy')))
        ) referencia
        INNER JOIN 
        (SELECT ID_PRODUCTO,ID_SUCURSAL,PRECIO_VENTA
        FROM (PRODUCTO_SUCURSAL))precioProducto
        ON (referencia.ID_SUCURSAL = precioProducto.ID_SUCURSAL AND referencia.ID_PRODUCTO = precioProducto.ID_PRODUCTO )
        )
    WHERE referencia.ID_SUCURSAL = 1
    GROUP BY referencia.ID_SUCURSAL;
--Con promocion
SELECT  NVL(SUM(MONTO),0) AS DINERO_RECOLECTADO
    FROM(VENTA_PRODUCTO)
    WHERE (ID_SUCURSAL = 1 AND (FECHA BETWEEN to_date('15/01/2021', 'dd/mm/yyyy') AND  to_date('15/01/2022', 'dd/mm/yyyy')))
    GROUP BY ID_SUCURSAL;
/*Consulta para gerente general*/
--Con promocion
SELECT ID,NOMBRE,TELEFONO,DIRECCION,CIUDAD,DINERO_RECOLECTADO
    FROM
    (
        (
        SELECT  ID_SUCURSAL,NVL(SUM(MONTO),0) AS DINERO_RECOLECTADO
        FROM(VENTA_PRODUCTO)
        WHERE ((FECHA BETWEEN to_date('15/01/2021', 'dd/mm/yyyy') AND  to_date('15/01/2022', 'dd/mm/yyyy')))
        GROUP BY ID_SUCURSAL)sumaSucursal
        LEFT OUTER JOIN 
        SUCURSAL
        ON(sumaSucursal.ID_SUCURSAL = SUCURSAL.ID)
    );
--Sin promocion
SELECT ID,NOMBRE,TELEFONO,DIRECCION,CIUDAD,DINERO_RECOLECTADO
    FROM
    (
        (
        SELECT referencia.ID_SUCURSAL,SUM( precioProducto.PRECIO_VENTA) AS DINERO_RECOLECTADO
        FROM(
        (SELECT ID_SUCURSAL,ID_PRODUCTO
        FROM (VENTA_PRODUCTO)
        WHERE ((FECHA BETWEEN to_date('03/11/2011', 'dd/mm/yyyy') AND  to_date('03/11/2015', 'dd/mm/yyyy')))
        ) referencia
        INNER JOIN 
        (SELECT ID_PRODUCTO,ID_SUCURSAL,PRECIO_VENTA
        FROM (PRODUCTO_SUCURSAL))precioProducto
        ON (referencia.ID_SUCURSAL = precioProducto.ID_SUCURSAL AND referencia.ID_PRODUCTO = precioProducto.ID_PRODUCTO )
        )
        GROUP BY referencia.ID_SUCURSAL)sumaSucursal
        LEFT OUTER JOIN 
        SUCURSAL
        ON(sumaSucursal.ID_SUCURSAL = SUCURSAL.ID)
    );
