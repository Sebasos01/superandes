package uniandes.isis2304.superandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

class SQLPedido {
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
	public SQLPedido (PersistenciaSuperandes ps)
	{
		this.ps = ps;
	}
	
	// Sebastian
	public long adicionarPedido(PersistenceManager pm,long idPedido,String idProducto,String idProveedor,long idSucursal,long cantidadProducto,long PrecioTotal, String inicio,long diasEntrega,String estado,String llegada) {
		String sql = "INSERT INTO"+ps.darTablaPedido()+"(NUM_PEDIDO, ID_PRODUCTO, ID_PROVEEDOR, ID_SUCURSAL, CANTIDAD_PRODUCTO, PRECIO_TOTAL, INICIO, DIAS_ENTREGA, ESTADO, LLEGADA) VALUES(?,?,?,?,?,?,?,?,?,?)";
		 Query q = pm.newQuery(SQL, sql);
		 q.setParameters(idPedido,idProducto,idProveedor,idSucursal,cantidadProducto,PrecioTotal,inicio,diasEntrega,estado,llegada);
		 return (long) q.executeUnique();  
	}
	public void registrarLlegadaPedido() {
		
	}
	// Sebastian
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar elas 20 primeras promociones que se acabaron en el menor tiempo
	 * @param pm - El manejador de persistencia
	 */
	
	public List<Object> comprasProveedorUnaSucursal(PersistenceManager pm, long idSucursal)
	{
		
		String sql = "SELECT * FROM ("+ ps.darTablaPedido()+") "; 
		sql += "WHERE (ESTADO = 'entregado' AND ID_SUCURSAL =?)";
		sql += "ORDER BY ID_PROVEEDOR";
		 Query q = pm.newQuery(SQL, sql);
		 q.setParameters(idSucursal);
		 /*
		 List<Object> list = q.executeList();
		 System.out.println(list);*/
		 return q.executeList();
	}
	
	public List<Object> comprasProveedorTodasSucursales(PersistenceManager pm)
	{
		
		String sql = "SELECT * FROM ("+ ps.darTablaPedido()+") "; 
		sql += "WHERE (ESTADO = 'entregado')";
		sql += "ORDER BY ID_PROVEEDOR";
		 Query q = pm.newQuery(SQL, sql);
		 /*
		 List<Object> list = q.executeList();
		 System.out.println(list);*/
		 return q.executeList();
	}
	
}
