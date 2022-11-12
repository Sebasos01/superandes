
/*Consulta para gerente de sucursal*/

SELECT * 
FROM (
PEDIDO
)
WHERE (ESTADO = 'Entregado' AND ID_SUCURSAL =3)
ORDER BY ID_PROVEEDOR;

/*Consulta para gerente de supermercado*/
SELECT * 
FROM (
PEDIDO
)
WHERE (ESTADO = 'Entregado' )
ORDER BY ID_SUCURSAL, ID_PROVEEDOR;