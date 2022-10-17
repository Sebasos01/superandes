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
		log.info ("Obteniendo sucurusal con del usuario con id: " + idUsuario);
		long id = ps.obtenerSucursalPorIdUsuario(idUsuario);
		log.info ("id de la sucursal obtenida: " + id);
		return id;
	}
	
	/**
	 * Crea el usuario administrador de datos
	 * Verifica que no exista
	 * Si no existe, lo crea con sus valores default
	 */
	public void crearAdminDatos() {
		log.info ("Verificando que el tipo usuario admin datos no exista");
		TipoUsuario tu = ps.obtenerTipoUsuarioPorNombre("administrador de datos");
		if (tu == null) {
			adicionarTipoUsuario("administrador de datosS", "N");
		} else {
			log.info ("El tipo usuario admin datos ya existe, por lo tanto no se crea");
		}
		log.info ("Verificando que el usuario admin datos no exista");
		List<Usuario> admin = ps.darUsuarios();
		if (admin.size() == 0) {
			log.info ("El usuario admin datos no existe, creando usuario");
			ps.registrarUsuario("100", "CC", "John Doe", "admin@superandes.com",
					"123", ps.obtenerTipoUsuarioPorNombre("administrador de datos").getId(), null);
			log.info ("Usuario admin datos creado");
		} else {
			log.info ("El usuario admin datos ya existe, por lo tanto no se crea");
		}
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
	public TipoUsuario adicionarTipoUsuario (String nombre, String esCliente)
	{
        log.info ("Adicionando Tipo de usuario: " + nombre);
        TipoUsuario tipoUsuario = ps.adicionarTipoUsuario (nombre, esCliente);
        log.info ("Adicionando Tipo de usuario: " + tipoUsuario);
        return tipoUsuario;
	}
	
	/**
	 * Método que retorna todas los tipos de usuarios de superandes
	 * @return una lista con todos los tipos de usuarios de superandes
	 */
	public List<VOTipoUsuario> obtenerListaTipoUsuario() {
		log.info ("Consultando Tipos de usario");
		List<VOTipoUsuario> lista =  ps.obtenerListaTipoUsuario().stream()
				.map(tu -> (VOTipoUsuario) tu).toList();
        log.info ("Consultando Tipos de usuario: " + lista.size() + " existentes");
		return lista;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los USUARIO
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un usuario
	 * Adiciona entradas al log de la aplicación
	 * @param todos los atributos
	 * @return El objeto TipoUsuario adicionado. null si ocurre alguna Excepción
	 */
	public Usuario registrarUsuario(String documento, String tipo_documento, String nombre, String email,
			String contrasena, long id_tipo, Long id_sucursal)
	{
        log.info ("Adicionando Tipo de usuario: " + nombre);
        Usuario usuario = ps.registrarUsuario(documento, tipo_documento, nombre, email,
    			contrasena, id_tipo, id_sucursal);
        log.info ("Adicionando Tipo de usuario: " + usuario);
        return usuario;
	}
	
	/**
	 * Método que retorna todas los usuarios de superandes
	 * @return una lista con todos los usuarios de superandes
	 */
	public List<VOUsuario> darUsuarios() {
		log.info ("Consultando usuarios");
		List<VOUsuario> lista =  ps.darUsuarios().stream()
				.map(tu -> (VOUsuario) tu).toList();
        log.info ("Consultando los usuario: " + lista.size() + " existentes");
		return lista;
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
	 * 			Métodos para manejar ALMACEN
	 *****************************************************************/
	public List<Object> indiceOcupacionVolumenUnaSucursal(long idSucursal)
	{
		log.info ("Consultando indice de ocupacion por volumen de sucursal "+String.valueOf(idSucursal));
		List<Object> tuplas = ps.indiceOcupacionVolumenUnaSucursal(idSucursal);
		log.info ("Finaliza consulta indice de ocupacion por volumen de sucursal "+String.valueOf(idSucursal));
		return tuplas;
	}
	
	public List<Object> indiceOcupacionVolumenTodasSucursales()
	{
		log.info ("Consultando indice de ocupacion por volumen");
		List<Object> tuplas = ps.indiceOcupacionVolumenTodasSucursales();
		log.info ("Finaliza consulta indice de ocupacion por volumen");
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
