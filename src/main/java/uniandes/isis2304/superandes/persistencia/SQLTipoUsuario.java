/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Superandes Uniandes
 * @version 1.0
 * @author Sebastián Ospino
 * Octubre del 2022
 * 
 * Revisado por:
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.superandes.persistencia;

import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import uniandes.isis2304.superandes.negocio.TipoUsuario;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto TIPO DE USUARIO de Superandes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Sebastián Ospino
 */
class SQLTipoUsuario
{
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
	public SQLTipoUsuario (PersistenciaSuperandes ps)
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
	public long adicionarTipoUsuario (PersistenceManager pm, long idTipoBebida, String nombre, String esCliente) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + ps.darTablaTipoUsuario  () + "(id, nombre, es_cliente) values (?, ?, ?)");
        q.setParameters(idTipoBebida, nombre, esCliente);
        return (long) q.executeUnique();            
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS TIPOS DE USUARIO de la 
	 * base de datos de Superandes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos TIPO_USUARIO
	 */
	public List<TipoUsuario> obtenerListaTipoUsuario (PersistenceManager pm) 
	{
        Query q = pm.newQuery(SQL, "SELECT * FROM " + ps.darTablaTipoUsuario());
        q.setResultClass(TipoUsuario.class);
        return (List<TipoUsuario>) q.executeList();          
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar un tipo de usuario por nombre
	 * base de datos de Superandes
	 * @param nombre - El nombre que se busca
	 * @param pm - El manejador de persistencia
	 * @return El tipo de usuario que concuerca con el nombre
	 */
	public TipoUsuario obtenerTipoUsuarioPorNombre(String nombre, PersistenceManager pm) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + ps.darTablaTipoUsuario() + " WHERE NOMBRE = ?");
        q.setResultClass(TipoUsuario.class);
        q.setParameters(nombre);
        return (TipoUsuario) q.executeUnique(); 
	}

	public String darNombreTipo(PersistenceManager pm, long id_tipo) {
		Query q = pm.newQuery(SQL, "SELECT NOMBRE FROM " + ps.darTablaTipoUsuario() + " WHERE ID = ?");
        q.setParameters(id_tipo);
        return (String) q.executeUnique(); 
	}

	

}
