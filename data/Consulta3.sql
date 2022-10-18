/*Consulta para gerente de sucursal*/
--Indice de ocupacion por peso

SELECT ALMACEN.ID, ALMACEN.TIPO, CANTIDAD_ACTUAL/CAPACIDAD_PESO AS INDICE_OCUPACION
FROM 
(
(
    SELECT ID_ALMACEN, SUM(CANTIDAD_PRODUCTO) AS CANTIDAD_ACTUAL --Se obtiene cantidad actual por almacen
    FROM (
    
        SELECT ID_ALMACEN, ID_PRODUCTO, NVL(CANTIDAD,0)*NVL(CANTIDAD_PRESENTACION,0) AS CANTIDAD_PRODUCTO
        FROM
        (
            PRODUCTO_ALMACEN
            LEFT OUTER JOIN --para que aparezcan todos los productos de la tabla producto almacen
            (SELECT CODIGO_BARRAS,NVL(CANTIDAD_PRESENTACION,0) AS CANTIDAD_PRESENTACION -- se obtienen los productos cuya unidadad de medida es en gramos
            FROM (PRODUCTO)
            WHERE UNIDAD_MEDIDA = 'gr')ProductosGramo
            ON (PRODUCTO_ALMACEN.ID_PRODUCTO =productosGramo.CODIGO_BARRAS)
        )
    )
    GROUP BY ID_ALMACEN
    ) almacenActual
INNER JOIN 
ALMACEN 
ON (almacenActual.ID_ALMACEN = ALMACEN.ID)
)
WHERE (ALMACEN.ID_SUCURSAL = 3); --Sucursal del gerente

--Indice de ocupacion por volumen
SELECT ALMACEN.ID AS ID_ALMACEN, ALMACEN.TIPO, CANTIDAD_ACTUAL/CAPACIDAD_VOLUMEN AS INDICE_OCUPACION, CAPACIDAD_VOLUMEN
FROM 
(
(
    SELECT ID_ALMACEN, SUM(CANTIDAD_PRODUCTO) AS CANTIDAD_ACTUAL --Se obtiene cantidad actual por almacen
    FROM (
    
        SELECT ID_ALMACEN, ID_PRODUCTO, NVL(CANTIDAD,0)*NVL(CANTIDAD_PRESENTACION,0) AS CANTIDAD_PRODUCTO
        FROM
        (
            PRODUCTO_ALMACEN
            LEFT OUTER JOIN --para que aparezcan todos los productos de la tabla producto almacen
            (SELECT CODIGO_BARRAS,NVL(CANTIDAD_PRESENTACION,0) AS CANTIDAD_PRESENTACION -- se obtienen los productos cuya unidadad de medida es en gramos
            FROM (PRODUCTO)
            WHERE UNIDAD_MEDIDA = 'ml')ProductosGramo
            ON (PRODUCTO_ALMACEN.ID_PRODUCTO =productosGramo.CODIGO_BARRAS)
        )
    )
    GROUP BY ID_ALMACEN
    ) almacenActual
INNER JOIN 
ALMACEN 
ON (almacenActual.ID_ALMACEN = ALMACEN.ID)
)
WHERE (ALMACEN.ID_SUCURSAL = 3); --Sucursal del gerente
/*Consulta para gerente de supermercado*/
--Indice de ocupacion por peso

SELECT ALMACEN.ID, ALMACEN.TIPO, CANTIDAD_ACTUAL/CAPACIDAD_PESO AS INDICE_OCUPACION, ALMACEN.ID_SUCURSAL
FROM 
(
(
    SELECT ID_ALMACEN, SUM(CANTIDAD_PRODUCTO) AS CANTIDAD_ACTUAL --Se obtiene cantidad actual por almacen
    FROM (
    
        SELECT ID_ALMACEN, ID_PRODUCTO, NVL(CANTIDAD,0)*NVL(CANTIDAD_PRESENTACION,0) AS CANTIDAD_PRODUCTO
        FROM
        (
            PRODUCTO_ALMACEN
            LEFT OUTER JOIN --para que aparezcan todos los productos de la tabla producto almacen
            (SELECT CODIGO_BARRAS,NVL(CANTIDAD_PRESENTACION,0) AS CANTIDAD_PRESENTACION -- se obtienen los productos cuya unidadad de medida es en gramos
            FROM (PRODUCTO)
            WHERE UNIDAD_MEDIDA = 'gr')ProductosGramo
            ON (PRODUCTO_ALMACEN.ID_PRODUCTO =productosGramo.CODIGO_BARRAS)
        )
    )
    GROUP BY ID_ALMACEN
    ) almacenActual
INNER JOIN 
ALMACEN 
ON (almacenActual.ID_ALMACEN = ALMACEN.ID)
)
ORDER BY ALMACEN.ID_SUCURSAL; 

--Indice de ocupacion por volumen
SELECT ALMACEN.ID, ALMACEN.TIPO, CANTIDAD_ACTUAL/CAPACIDAD_VOLUMEN AS INDICE_OCUPACION,CAPACIDAD_VOLUMEN,ALMACEN.ID_SUCURSAL
FROM 
(
(
    SELECT ID_ALMACEN, SUM(CANTIDAD_PRODUCTO) AS CANTIDAD_ACTUAL --Se obtiene cantidad actual por almacen
    FROM (
    
        SELECT ID_ALMACEN, ID_PRODUCTO, NVL(CANTIDAD,0)*NVL(CANTIDAD_PRESENTACION,0) AS CANTIDAD_PRODUCTO
        FROM
        (
            PRODUCTO_ALMACEN
            LEFT OUTER JOIN --para que aparezcan todos los productos de la tabla producto almacen
            (SELECT CODIGO_BARRAS,NVL(CANTIDAD_PRESENTACION,0) AS CANTIDAD_PRESENTACION -- se obtienen los productos cuya unidadad de medida es en gramos
            FROM (PRODUCTO)
            WHERE UNIDAD_MEDIDA = 'ml')ProductosGramo
            ON (PRODUCTO_ALMACEN.ID_PRODUCTO =productosGramo.CODIGO_BARRAS)
        )
    )
    GROUP BY ID_ALMACEN
    ) almacenActual
INNER JOIN 
ALMACEN 
ON (almacenActual.ID_ALMACEN = ALMACEN.ID)
)
ORDER BY ALMACEN.ID_SUCURSAL; 




SELECT ALMACEN.ID, ALMACEN.TIPO, CANTIDAD_ACTUAL/CAPACIDAD_VOLUMEN AS INDICE_OCUPACION,CAPACIDAD_VOLUMEN
FROM(
(SELECT ID_ALMACEN, SUM(CANTIDAD_PRODUCTO) AS CANTIDAD_ACTUAL     
    FROM ( 
    SELECT ID_ALMACEN, ID_PRODUCTO, NVL(CANTIDAD,0)*NVL(CANTIDAD_PRESENTACION,0)AS CANTIDAD_PRODUCTO
        FROM(PRODUCTO_ALMACEN 
        LEFT OUTER JOIN 
        (SELECT CODIGO_BARRAS,NVL(CANTIDAD_PRESENTACION,0) AS CANTIDAD_PRESENTACION
        FROM (PRODUCTO) WHERE UNIDAD_MEDIDA = 'ml')productosGramo
        ON (PRODUCTO.ID_PRODUCTO =productosGramo.CODIGO_BARRAS)) )
    GROUP BY ID_ALMACEN)almacenActual 
    INNER JOIN 
    ALMACEN ON (almacenActual.ID_ALMACEN = ALMACEN.ID))
    
WHERE (ALMACEN.ID_SUCURSAL = 3)