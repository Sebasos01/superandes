package uniandes.isis2304.superandes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

class SQLSucursal {
	 
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
		public SQLSucursal (PersistenciaSuperandes ps)
		{
			this.ps = ps;
		}
		
		/**
		 * Crea y ejecuta la sentencia SQL para adicionar un TIPO_USUARIO a la base de datos de Superandes
		 * @param pm - El manejador de persistencia
		 * @param idTipoUsuario - El identificador del tipo de usuario
		 * @param nombre - El nombre del tipo de usuario
		 * @return EL número de tuplas insertadas
		 */
		public long adicionarSucursal (PersistenceManager pm, long idSucursal, String nombre, String telefono,String direccion, String ciudad) 
		{
	        Query q = pm.newQuery(SQL, "INSERT INTO " + ps.darTablaSucursal  () + "(id, nombre, telefono,direccion,ciudad) values (?, ?, ?,?,?)");
	        q.setParameters(idSucursal,nombre,telefono,direccion,ciudad);
	        return (long) q.executeUnique();            
		}

}
