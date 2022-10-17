package uniandes.isis2304.superandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

class SQLAlmacen {
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
	public SQLAlmacen (PersistenciaSuperandes ps)
	{
		this.ps = ps;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar elas 20 primeras promociones que se acabaron en el menor tiempo
	 * @param pm - El manejador de persistencia
	 */
	
	public List<Object> indiceOcupacionVolumenUnaSucursal(PersistenceManager pm, long idSucursal)
	{
		
		String productosMl = " SELECT ID_ALMACEN, ID_PRODUCTO, NVL(CANTIDAD,0)*NVL(CANTIDAD_PRESENTACION,0) AS CANTIDAD_PRODUCTO ";
		productosMl += "FROM("+ps.darTablaProductoAlmacen(); 
		productosMl += " LEFT OUTER JOIN ";
		productosMl += "(SELECT CODIGO_BARRAS,NVL(CANTIDAD_PRESENTACION,0) AS CANTIDAD_PRESENTACION ";
		productosMl += "FROM ("+ps.darTablaProducto()+") ";
		productosMl += "WHERE UNIDAD_MEDIDA = 'ml')productosGramo ";
		productosMl += "ON ("+ps.darTablaProductoAlmacen()+".ID_PRODUCTO =productosGramo.CODIGO_BARRAS)) "; 
		
		String cantidadActual = "(SELECT ID_ALMACEN, SUM(CANTIDAD_PRODUCTO) AS CANTIDAD_ACTUAL ";
				cantidadActual += "    FROM ("+productosMl+") GROUP BY ID_ALMACEN)";
				
		
		String sql = "SELECT ALMACEN.ID, ALMACEN.TIPO, CANTIDAD_ACTUAL/CAPACIDAD_VOLUMEN AS INDICE_OCUPACION,CAPACIDAD_VOLUMEN ";
				sql += "FROM("+cantidadActual + "almacenActual INNER JOIN " + ps.darTablaAlmacen()+ " ON (almacenActual.ID_ALMACEN = "+ ps.darTablaAlmacen()+".ID))";
				sql += "WHERE (ALMACEN.ID_SUCURSAL = 3)";
		 Query q = pm.newQuery(SQL, sql);
		 q.setParameters(idSucursal);
		 /*
		 List<Object> list = q.executeList();
		 System.out.println(list);*/
		 return q.executeList();
	}
	
	public List<Object> indiceOcupacionVolumenTodasSucursales(PersistenceManager pm)
	{
		
		String productosMl = " SELECT ID_ALMACEN, ID_PRODUCTO, NVL(CANTIDAD,0)*NVL(CANTIDAD_PRESENTACION,0) AS CANTIDAD_PRODUCTO ";
				productosMl +=  "FROM("+ps.darTablaProductoAlmacen(); 
				productosMl +=  " LEFT OUTER JOIN";
				productosMl += "(SELECT CODIGO_BARRAS,NVL(CANTIDAD_PRESENTACION,0) AS CANTIDAD_PRESENTACION ";
				productosMl +="	FROM ("+ps.darTablaProducto()+") ";
				productosMl += "WHERE UNIDAD_MEDIDA = 'ml')productosGramo ";
				productosMl +="ON ("+ps.darTablaProductoAlmacen()+".ID_PRODUCTO =productosGramo.CODIGO_BARRAS)) "; 
				
		String cantidadActual = "(SELECT ID_ALMACEN, SUM(CANTIDAD_PRODUCTO) AS CANTIDAD_ACTUAL ";
				cantidadActual += "    FROM ("+productosMl+") GROUP BY ID_ALMACEN)";
				
		
		String sql = "SELECT ALMACEN.ID, ALMACEN.TIPO, CANTIDAD_ACTUAL/CAPACIDAD_VOLUMEN AS INDICE_OCUPACION, CAPACIDAD_VOLUMEN,ALMACEN.ID_SUCURSAL ";
				sql += "FROM("+cantidadActual + "almacenActual INNER JOIN " + ps.darTablaAlmacen()+ " ON (almacenActual.ID_ALMACEN ="+ ps.darTablaAlmacen()+".ID))";
				sql += "ORDER BY ALMACEN.ID_SUCURSAL";
		 Query q = pm.newQuery(SQL, sql);
		 /*
		 List<Object> list = q.executeList();
		 System.out.println(list);*/
		 return q.executeList();
	}
	

}
