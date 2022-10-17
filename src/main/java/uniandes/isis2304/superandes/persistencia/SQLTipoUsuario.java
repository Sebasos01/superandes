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
	public long adicionarTipoUsuario (PersistenceManager pm, long idTipoBebida, String nombre) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + ps.darTablaTipoUsuario  () + "(id, nombre) values (?, ?)");
        q.setParameters(idTipoBebida, nombre);
        return (long) q.executeUnique();            
	}
	
	public List<Object> obtenerListaTipoUsuario (PersistenceManager pm) 
	{
        Query q = pm.newQuery(SQL, "SELECT * FROM " + ps.darTablaTipoUsuario());
        List<Object> lista = q.executeList();
        System.out.println(lista.size());
        return lista;            
	}

}
