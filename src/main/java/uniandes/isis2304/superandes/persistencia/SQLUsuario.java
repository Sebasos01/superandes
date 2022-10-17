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

import uniandes.isis2304.superandes.negocio.Usuario;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto USUARIO de Superandes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Sebastián Ospino
 */
class SQLUsuario
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
	public SQLUsuario(PersistenciaSuperandes ps)
	{
		this.ps = ps;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un USUARIO a la base de datos de Superandes
	 * @return EL número de tuplas insertadas
	 */
	public long registrarUsuario(long codigo_usuario, String documento, String tipo_documento, String nombre, String email,
			String contrasena, long id_tipo, Long id_sucursal, PersistenceManager pm) 
	{
		String qs = "insert into " + ps.darTablaUsuario() + " (CODIGO_USUARIO, DOCUMENTO, TIPO_DOCUMENTO, NOMBRE, EMAIL, CONTRASENA, ID_TIPO, ID_SUCURSAL)";
		qs += "values (?, ?, ?, ?, ?, ?, ?, ?)";
        Query q = pm.newQuery(SQL, qs);
        q.setParameters(codigo_usuario, documento, tipo_documento, nombre, email, contrasena, id_tipo, id_sucursal);
        return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS USUARIO de la 
	 * base de datos de Superandes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos USUARIO
	 */
	public List<Usuario> darUsuarios(PersistenceManager pm) 
	{
        Query q = pm.newQuery(SQL, "SELECT * FROM " + ps.darTablaUsuario());
        q.setResultClass(Usuario.class);
        return (List<Usuario>) q.executeList();          
	}
	
	/**
	 * Obtiene el id de la sucursal de determinado usuario
	 * Lo hace a través de una sentencia SQL
	 * @param idUsuario el id del usuario del que se quiere saber su sucursal asociada
	 * @param pm - El manejador de persistencia
	 * @return el id de la sucursal asociada al usuario con dicho id
	 */
	public long obtenerSucursalPorIdUsuario(long idUsuario, PersistenceManager pm) {
		Query q = pm.newQuery(SQL, "SELECT ID_SUCURSAL FROM " + ps.darTablaUsuario() + " WHERE CODIGO_USUARIO = ?");
		q.setParameters(idUsuario);
		return (long) q.executeUnique();
	}

}
