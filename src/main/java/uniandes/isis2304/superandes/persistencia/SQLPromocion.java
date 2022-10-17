package uniandes.isis2304.superandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

class SQLPromocion {

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
	public SQLPromocion (PersistenciaSuperandes ps)
	{
		this.ps = ps;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar elas 20 primeras promociones que se acabaron en el menor tiempo
	 * @param pm - El manejador de persistencia
	 */
	
	public List<Object> darPromocionesPopularesSucursal(PersistenceManager pm, long idSucursal)
	{
		
		String sql ="SELECT * FROM("+ps.darTablaPromocion()+")WHERE (rownum<= 20 AND ID_SUCURSAL = ?  AND FIN IS NOT NULL) ORDER BY (FIN-INICIO) ASC";
		 Query q = pm.newQuery(SQL, sql);
		 //q.setParameters(idSucursal);
		 List<Object> list = q.executeList();
		 System.out.println(list);
		 return q.executeList();
	}
	
	public List<Object> darPromocionesPopularesTodasSucursales(PersistenceManager pm)
	{
		String sql = "SELECT * FROM("+ps.darTablaPromocion()+") WHERE (rownum<= 20  AND FIN IS NOT NULL) ORDER BY (FIN-INICIO) ASC";
		 Query q = pm.newQuery(SQL, sql);
		 return q.executeList();
	}
	
	
}
