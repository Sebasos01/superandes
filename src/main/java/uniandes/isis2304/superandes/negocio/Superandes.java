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

package uniandes.isis2304.superandes.negocio;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import com.google.gson.JsonObject;

import uniandes.isis2304.superandes.persistencia.PersistenciaSuperandes;

/**
 * Clase principal del negocio
 * Sarisface todos los requerimientos funcionales del negocio
 *
 * @author Sebastián Ospino
 */
public class Superandes 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(Superandes.class.getName());
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia
	 */
	private PersistenciaSuperandes ps;
	
	/**
     * Identificador del usuario actual
     */
	protected long idUsuario;
	
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	
	/**
	 * El constructor que recibe los nombres de las tablas en tableConfig
	 * @param tableConfig - Objeto Json con los nombres de las tablas y de la unidad de persistencia
	 */
	public Superandes (JsonObject tableConfig)
	{
		ps = PersistenciaSuperandes.getInstance (tableConfig);
		new ManejadorPromociones().start();
	}
	
	/**
	 * Cierra la conexión con la base de datos (Unidad de persistencia)
	 */
	public void cerrarUnidadPersistencia ()
	{
		ps.cerrarUnidadPersistencia ();
	}
	
	/**
	 * Obtiene el id de la sucursal de determinado usuario
	 * @param idUsuario el id del usuario del que se quiere saber su sucursal asociada
	 * @return el id de la sucursal asociada al usuario con dicho id
	 */
	public long obtenerSucursalPorIdUsuario(long idUsuario) {
		return 0;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los TIPOS DE USUARIO
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un tipo de usuario
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de usuario
	 * @return El objeto TipoUsuario adicionado. null si ocurre alguna Excepción
	 */
	public TipoUsuario adicionarTipoUsuario (String nombre)
	{
        log.info ("Adicionando Tipo de usuario: " + nombre);
        TipoUsuario tipoUsuario = ps.adicionarTipoUsuario (nombre);		
        log.info ("Adicionando Tipo de usuario: " + tipoUsuario);
        return tipoUsuario;
	}
	
	
	/* ****************************************************************
	 * 			Métodos para manejar VENTA PRODUCTO
	 *****************************************************************/
	/*Consulta 1*/
	public long darDineroFechasUnaSucursal (String fechaInicio, String fechaFinal, long idSucursal)
	{
        log.info ("Consultando dinero en el rango de fechas " + String.valueOf(fechaInicio)+" , "+String.valueOf(fechaFinal) + "de la sucursal " + String.valueOf(idSucursal));
        long dineroRecolectado = ps.darDineroFechasUnaSucursal(fechaInicio,fechaFinal,idSucursal);		
        log.info ("Finaliza consulta de dinero en el rango de fechas " + String.valueOf(fechaInicio)+" , "+String.valueOf(fechaFinal) + "de la sucursal " + String.valueOf(idSucursal) );
        return dineroRecolectado;
	}
	
	public List<Object []> darDineroFechasTodasSucursales (String fechaInicio, String fechaFinal)
	{
        log.info ("Consultando sucursales y el dinero que han recolectado en el rango de fechas: "+String.valueOf(fechaInicio)+" , "+String.valueOf(fechaFinal) );
        List<Object []> tuplas = ps.darDineroFechasTodasSucursales (fechaInicio,fechaFinal); // Lista de tuplas de la forma (Sucursal, dineroRecolectado)
        log.info ("Finaliza consulta de sucursales y dinero que han recolectado en rango de fechas: "+String.valueOf(fechaInicio)+" , "+String.valueOf(fechaFinal) + "de la sucursal " );
        return tuplas;
	}

	/* ****************************************************************
	 * 			Métodos para manejar PROMOCION
	 *****************************************************************/
	public List<VOPromocion> darPromocionesPopularesSucursal(long idSucursal)
	{
		log.info ("Consultando promociones mas populares en la sucursal" + String.valueOf(idSucursal));
		/*
		List<VOPromocion> voPromociones = new LinkedList<VOPromocion> ();
		for (Promocion prom: ps.darPromocionesPopularesSucursal (idSucursal))
		{
			voPromociones.add (prom);
		}
        return voPromociones;
	*/
        List<VOPromocion> tuplas = ps.darPromocionesPopularesSucursal (idSucursal).stream().map (p -> (VOPromocion) p ).toList(); // Lista de tuplas de la forma (Sucursal, dineroRecolectado)
        return tuplas;
	
	}
	public List<Promocion> darPromocionesPopularesTodasSucursales()
	{
		log.info ("Consultando promociones mas populares");
        List<Promocion> tuplas = ps.darPromocionesPopularesTodasSucursales (); // Lista de tuplas de la forma (Sucursal, dineroRecolectado)
        log.info ("Finaliza consulta promociones mas populares");
        return tuplas;
	
	}
	
	/* ****************************************************************
	 * 			Métodos para administración
	 *****************************************************************/

	/**
	 * Elimina todas las tuplas de todas las tablas de la base de datos de Superandes
	 * @return Un arreglo con un arreglo que indica cuantas filas se eliminaron por tabla
	 */
	public long [] limpiarSuperandes()
	{
        log.info ("Limpiando la BD de Superandes");
        long [] borrados = ps.limpiarSuperandes();	
        log.info ("Limpiando la BD de Superandes: Listo!");
        return borrados;
	}
}
