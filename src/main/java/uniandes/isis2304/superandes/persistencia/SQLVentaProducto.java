package uniandes.isis2304.superandes.persistencia;

import java.math.BigDecimal;
import java.util.List;

import javax.jdo.PersistenceManager;

import javax.jdo.Query;

/**
 * Clase que contiene métodos útiles de acceso a la base de datos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Sebastián Ospino
 * @author Ana Sofía Castellanos
 */

class SQLVentaProducto {
	
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaSuperandes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaSuperandes ps;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLVentaProducto (PersistenciaSuperandes ps)
	{
		this.ps = ps;
	}
	
	
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar el dinero recolectado por ventas en un rango de fechas (Una sucursal)
	 * @param pm - El manejador de persistencia
	 * @param fechaInicio - fecha inicio
	 * @param fechaFinal- El nombre del tipo de usuario
	 * @return EL número de tuplas insertadas
	 */
	
	public long darDineroFechasUnaSucursal (PersistenceManager pm, String fechaInicio, String fechaFinal, long idSucursal)
	{
		/*
	SELECT  NVL(SUM(MONTO),0) AS DINERO_RECOLECTADO
    FROM(VENTA_PRODUCTO)
    WHERE (ID_SUCURSAL = 1 AND (FECHA BETWEEN to_date('15/01/2021', 'dd/mm/yyyy') AND  to_date('15/01/2022', 'dd/mm/yyyy')))
    GROUP BY ID_SUCURSAL;
        */
		
		//Se seleccionan las ventas de productos que pertenezcan a la sucursal y esten en ese rango de fechas
		
		String sql= "SELECT NVL(SUM(MONTO),0) AS DINERO_RECOLECTADO ";
		sql +="FROM ( " + ps.darTablaVentaProducto() +")";
		sql+=" WHERE ((FECHA BETWEEN to_date(?, 'dd/mm/yyyy') AND  to_date(?, 'dd/mm/yyyy'))AND ID_SUCURSAL=?)";
		
	    Query q = pm.newQuery(SQL, sql);
	    q.setParameters(fechaInicio, fechaFinal, idSucursal);
	    
	    //Retorna dinero recolectado en dicha sucursal en ese rango de fechas
	   
	    long res = 0; 
	    try {
	    	res= ((BigDecimal) q.executeUnique()).longValue ();
	    }
	    catch 	(Exception e) 
	    	{
				res =0;
			}
	    return (res) ;
	}
	public List<Object> darDineroFechasTodasSucursales (PersistenceManager pm, String fechaInicio, String fechaFinal)
	{
		/*
		SELECT referencia.ID_SUCURSAL, SUM( precioProducto.PRECIO_VENTA) AS DINERO_RECOLECTADO
    FROM
    (
        (SELECT ID_SUCURSAL,ID_PRODUCTO
        FROM (VENTA_PRODUCTO)
        WHERE ((FECHA BETWEEN to_date('03/11/2011', 'dd/mm/yyyy') AND  to_date('03/11/2015', 'dd/mm/yyyy')))
        ) referencia
        INNER JOIN 
        (SELECT ID_PRODUCTO,ID_SUCURSAL,PRECIO_VENTA
        FROM (PRODUCTO_SUCURSAL))precioProducto
        ON (referencia.ID_SUCURSAL = precioProducto.ID_SUCURSAL AND referencia.ID_PRODUCTO = precioProducto.ID_PRODUCTO )
        )
    GROUP BY referencia.ID_SUCURSAL;
        */
		/*
		//Se seleccionan las ventas de productos que pertenezcan a la sucursal y esten en ese rango de fechas
		String sumaSucursal= "(SELECT ID_SUCURSAL,NVL(SUM(MONTO),0) AS DINERO_RECOLECTADO";
		sumaSucursal +="FROM ( " + ps.darTablaVentaProducto() +")";
		sumaSucursal+=" WHERE (FECHA BETWEEN to_date(?, 'dd/mm/yyyy') AND  to_date(?, 'dd/mm/yyyy'))";
		sumaSucursal+=" GROUP BY (ID_SUCURSAL))";

		String sql = "SELECT ID, NOMBRE,TELEFONO,DIRECCION,CIUDAD,DINERO_RECOLECTADO"; 
		sql +="FROM("+sumaSucursal + "LEFT OUTER JOIN"+ps.darTablaSucursal()+" ON(sumaSucursal.ID_SUCURSAL ="+ ps.darTablaSucursal()+".ID))";
		
		
	    Query q = pm.newQuery(SQL, sql);
	    q.setParameters(fechaInicio, fechaFinal);
		return q.executeList();*/
		
		String sql = "SELECT * FROM VENTA_PRODUCTO";
		Query q = pm.newQuery(SQL, sql);
		return q.executeList();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar ventas en un rango de fechas par aun cliente dado
	 * @param pm - El manejador de persistencia
	 * @param fechaInicio - fecha inicio
	 * @param fechaFinal- El nombre del tipo de usuario
	 * @return EL número de tuplas insertadas
	 */
	public List<Object> darVentasClienteUnaSucursal(PersistenceManager pm, String fechaInicio, String fechaFinal, long idSucursal,long idUsuario)
	{
		
		String sql = "SELECT * FROM ("+ps.darTablaVentaProducto()+") ";
		sql += "WHERE ((FECHA BETWEEN to_date(?, 'dd/mm/yyyy') AND  to_date(?, 'dd/mm/yyyy')) "; 
		sql += "AND ID_SUCURSAL = ? AND ID_USUARIO = ? )"; 
						       
		 Query q = pm.newQuery(SQL, sql);
		 q.setParameters(fechaInicio,fechaFinal,idSucursal,idUsuario);
	
		 return q.executeList();
	}
	
	public List<Object> darVentasClienteTodasSucursales(PersistenceManager pm, String fechaInicio, String fechaFinal,long idUsuario)
	{
		
		String sql = "SELECT * FROM ("+ps.darTablaVentaProducto()+") ";
		sql += "WHERE ((FECHA BETWEEN to_date(?, 'dd/mm/yyyy') AND  to_date(?, 'dd/mm/yyyy')) "; 
		sql += "AND ID_USUARIO = ? )"; 
						       
		 Query q = pm.newQuery(SQL, sql);
		 q.setParameters(fechaInicio,fechaFinal,idUsuario);
	
		 return q.executeList();
	}
	
	
	

}
