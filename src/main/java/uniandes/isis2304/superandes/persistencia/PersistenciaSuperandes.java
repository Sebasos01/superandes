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

import java.math.BigDecimal;
import java.sql.Timestamp;

import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import uniandes.isis2304.superandes.negocio.Pedido;
import uniandes.isis2304.superandes.negocio.Producto;
import uniandes.isis2304.superandes.negocio.Promocion;
import uniandes.isis2304.superandes.negocio.Sucursal;
import uniandes.isis2304.superandes.negocio.TipoUsuario;
import uniandes.isis2304.superandes.negocio.Usuario;
import uniandes.isis2304.superandes.negocio.VentaProducto;

/**
 * Clase para el manejador de persistencia del proyecto Superandes
 * Traduce la información entre objetos Java y tuplas de la base de datos, en ambos sentidos
 * Sigue un patrón SINGLETON (Sólo puede haber UN objeto de esta clase) para comunicarse de manera correcta
 * con la base de datos
 * Se apoya en todas las clases SQL, que son 
 * las que realizan el acceso a la base de datos
 * 
 * @author Sebastián Ospino
 */
public class PersistenciaSuperandes 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(PersistenciaSuperandes.class.getName());
	
	/**
	 * Cadena para indicar el tipo de sentencias que se va a utilizar en una consulta
	 */
	public final static String SQL = "javax.jdo.query.SQL";

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * Atributo privado que es el único objeto de la clase - Patrón SINGLETON
	 */
	private static PersistenciaSuperandes instance;
	
	/**
	 * Fábrica de Manejadores de persistencia, para el manejo correcto de las transacciones
	 */
	private PersistenceManagerFactory pmf;
	
	/**
	 * Arreglo de cadenas con los nombres de las tablas de la base de datos, en su orden:
	 * SUPERANDES_SEQUENCE, ALMACEN, DEF_PROVEEDOR, DESCUENTO, PAGUE_N_LLEVE_M, PAQUETE, PEDIDO, PRODUCTO, PRODUCTO_ALMACEN, PRODUCTO_PROVEEDOR, PRODUCTO_SUCURSAL, PROMOCION, PROVEEDOR, SUCURSAL, SUCURSAL_PROVEEDOR, TIPO_USUARIO, USUARIO, VENTA_PRODUCTO
	 */
	private List <String> tablas;
	
	/**
	 * Atributo para el acceso a las sentencias SQL propias a PersistenciaSuperandes
	 */
	private SQLUtil sqlUtil;
	
	/**
	 * Atributo para el acceso a la tabla TIPO_USUARIO de la base de datos
	 */
	private SQLTipoUsuario sqlTipoUsuario;
	
	/**
	 * Atributo para el acceso a la tabla USUARIO de la base de datos
	 */
	private SQLUsuario sqlUsuario;
	private SQLVentaProducto sqlVentaProducto;
	private SQLPromocion sqlPromocion;
	private SQLAlmacen sqlAlmacen;
	private SQLPedido sqlPedido;
	private SQLSucursal sqlSucursal;
	private SQLProducto sqlProducto;
	
	/* ****************************************************************
	 * 			Métodos del MANEJADOR DE PERSISTENCIA
	 *****************************************************************/

	/**
	 * Constructor privado, que recibe los nombres de las tablas en un objeto Json - Patrón SINGLETON
	 * @param tableConfig - Objeto Json que contiene los nombres de las tablas y de la unidad de persistencia a manejar
	 */
	private PersistenciaSuperandes (JsonObject tableConfig)
	{
		crearClasesSQL ();
		tablas = leerNombresTablas (tableConfig);
		
		String unidadPersistencia = tableConfig.get ("unidadPersistencia").getAsString ();
		log.trace ("Accediendo unidad de persistencia: " + unidadPersistencia);
		pmf = JDOHelper.getPersistenceManagerFactory (unidadPersistencia);
	}
	
	/**
	 * Constructor que toma los nombres de las tablas de la base de datos del objeto tableConfig
	 * @param tableConfig - El objeto JSON con los nombres de las tablas
	 * @return Retorna el único objeto PersistenciaSuperandes existente - Patrón SINGLETON
	 */
	public static PersistenciaSuperandes getInstance (JsonObject tableConfig)
	{
		if (instance == null)
		{
			instance = new PersistenciaSuperandes (tableConfig);
		}
		return instance;
	}

	/**
	 * Cierra la conexión con la base de datos
	 */
	public void cerrarUnidadPersistencia ()
	{
		pmf.close ();
		instance = null;
	}
	
	/**
	 * Genera una lista con los nombres de las tablas de la base de datos
	 * @param tableConfig - El objeto Json con los nombres de las tablas
	 * @return La lista con los nombres del secuenciador y de las tablas
	 */
	private List <String> leerNombresTablas (JsonObject tableConfig)
	{
		JsonArray nombres = tableConfig.getAsJsonArray("tablas") ;

		List <String> resp = new LinkedList <String> ();
		for (JsonElement nom : nombres)
		{
			resp.add (nom.getAsString ());
		}

		return resp;
	}
	
	/**
	 * Crea los atributos de clases de apoyo SQL
	 */
	private void crearClasesSQL ()
	{
		sqlUtil = new SQLUtil(this);
		sqlTipoUsuario = new SQLTipoUsuario(this);
		sqlVentaProducto = new SQLVentaProducto(this);
		sqlPromocion = new SQLPromocion(this);
		sqlAlmacen = new SQLAlmacen(this);
		sqlUsuario = new SQLUsuario(this);
		sqlPedido = new SQLPedido(this);
		sqlSucursal = new SQLSucursal(this);
		sqlProducto = new SQLProducto(this);
	}

	/**
	 * @return La cadena de caracteres con el nombre del secuenciador de superandes
	 */
	public String darSeqSuperandes ()
	{
		return tablas.get (0);
	}
	
	/* ****************************************************************
	 * 			Métodos para dar los nombres de las TABLAS
	 *****************************************************************/
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de ProductoSucursal
	 */
	public String darTablaAlmacen() {
		return tablas.get (1);
	}
	public String darTablaPedido() {
		return tablas.get (6);
	}
	public String darTablaProducto() {
		return tablas.get (7);
	}
	
	public String darTablaProductoAlmacen() {
		
		return tablas.get (8);
	}
	public String darTablaProductoSucursal ()
	{
		return tablas.get (10);
	}
	public String darTablaPromocion ()
	{
		return tablas.get (11);
	}
	public String darTablaSucursal() {
		
		return tablas.get (13);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de USUA
	 */
	public String darTablaUsuario() {		
		return tablas.get (16);
	}
	

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de TipoUsuario de superandes
	 */
	
	public String darTablaTipoUsuario ()
	{
		return tablas.get (15);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de VentaProducto
	 */
	public String darTablaVentaProducto ()
	{
		return tablas.get (17);
	}
	
	/**
	 * Transacción para el generador de secuencia de Superandes
	 * Adiciona entradas al log de la aplicación
	 * @return El siguiente número del secuenciador de Superandes
	 */
	private long nextval ()
	{
        long resp = sqlUtil.nextval (pmf.getPersistenceManager());
        log.trace ("Generando secuencia: " + resp);
        return resp;
    }
	
	/**
	 * Extrae el mensaje de la exception JDODataStoreException embebido en la Exception e, que da el detalle específico del problema encontrado
	 * @param e - La excepción que ocurrio
	 * @return El mensaje de la excepción JDO
	 */
	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los TIPOS DE USUARIO
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla TIPO_USUARIO
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de usuario
	 * @return El objeto TipoUsuario adicionado. null si ocurre alguna Excepción
	 */
	public TipoUsuario adicionarTipoUsuario(String nombre, String esCliente)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idTipoUsuario = nextval ();
            long tuplasInsertadas = sqlTipoUsuario.adicionarTipoUsuario(pm, idTipoUsuario, nombre, esCliente);
            tx.commit();
            
            log.trace ("Inserción de tipo de usuario: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new TipoUsuario (idTipoUsuario, nombre, esCliente);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
  
	/**
	 * Método que retorna tipo de usuario por nombre
	 * @param nombre - el nombre que se quiere para la búsqueda
	 * @return el tipo de usuario que concuerda con el nombre
	 */
	public TipoUsuario obtenerTipoUsuarioPorNombre(String nombre) {
		return sqlTipoUsuario.obtenerTipoUsuarioPorNombre(nombre, pmf.getPersistenceManager());
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los USUARIO
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla USUARIO
	 * Adiciona entradas al log de la aplicación
	 * @param
	 * @return El objeto Usuario creado. Null si ocurre alguna Excepción
	 */
	public Usuario registrarUsuario(String documento, String tipo_documento, String nombre, String email,
			String contrasena, long id_tipo, Long id_sucursal)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long codigo_usuario = nextval ();
            long tuplasInsertadas = sqlUsuario.registrarUsuario(codigo_usuario, documento, tipo_documento, nombre, email,
        			contrasena, id_tipo, id_sucursal, pm);
            tx.commit();
            
            log.trace ("Inserción del usuario: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Usuario(codigo_usuario, documento, tipo_documento, nombre, email,
        			contrasena, id_tipo, id_sucursal);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	/**
	 * Método que retorna todas los usuarios
	 * @return una lista con todos los usuarios de superandes
	 */
	public List<Usuario> darUsuarios() {
		return sqlUsuario.darUsuarios(pmf.getPersistenceManager());
	}
	
	/**
	 * Obtiene el id de la sucursal de determinado usuario
	 * @param idUsuario el id del usuario del que se quiere saber su sucursal asociada
	 * @return el id de la sucursal asociada al usuario con dicho id
	 */
	public long obtenerSucursalPorIdUsuario(long idUsuario) {
		return sqlUsuario.obtenerSucursalPorIdUsuario(idUsuario, pmf.getPersistenceManager());
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar la SUCURSAL
	 *****************************************************************/
	public Sucursal adicionarSucursal(String nombre, String telefono,String direccion, String ciudad)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idSucursal = nextval ();
            long tuplasInsertadas = sqlSucursal.adicionarSucursal(pm,idSucursal, nombre, telefono,direccion, ciudad);
            tx.commit();
            
            log.trace ("Inserción de sucursal: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Sucursal(idSucursal,nombre, telefono,direccion, ciudad);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	/**
	 * Método que retorna todas los tipos de usuarios de superandes
	 * @return una lista con todos los tipos de usuarios de superandes
	 */
	public List<TipoUsuario> obtenerListaTipoUsuario() {
		return sqlTipoUsuario.obtenerListaTipoUsuario(pmf.getPersistenceManager());
	}
	/* ****************************************************************
	 * 			Métodos para manejar PRODUCTO
	 *****************************************************************/
	
	public List<Object []> darProductosPreciosUnaSucursal(long precioInicial, long precioFinal,long idSucursal)
	{
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object> tuplas = sqlProducto.darProductosPreciosUnaSucursal (pmf.getPersistenceManager(), precioInicial,precioFinal,idSucursal);
        for ( Object tupla : tuplas)
        {
			Object [] datos = (Object []) tupla;
			String codigoBarras = (String) datos [0];;
			String nombre = (String) datos [1];
			String marca = (String) datos [2];
			String unidadMedida = (String) datos [3];
			String presentacion = (String) datos [4];
			int cantidadPresentacion = ((BigDecimal) datos [5]).intValue ();
			int volumenEmpaque = ((BigDecimal) datos [6]).intValue ();
			int pesoEmpaque = ((BigDecimal) datos [7]).intValue ();
			String tipo = (String) datos [8];
			String categoria = (String) datos [9];
			Timestamp fechaVencimiento = (Timestamp) datos [10];
			int precioVenta= ((BigDecimal) datos [11]).intValue ();
			

			Object [] resp = new Object [2];
			resp [0] = new Producto(codigoBarras,nombre,marca,unidadMedida,presentacion,cantidadPresentacion,volumenEmpaque,pesoEmpaque,tipo,categoria,fechaVencimiento);
			resp [1] = precioVenta;	
			
			respuesta.add(resp);
        }

		return respuesta;
	}
	public List<Object []> darProductosPreciosTodasSucursales(long precioInicial, long precioFinal)
	{
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object> tuplas = sqlProducto.darProductosPreciosTodasSucursales (pmf.getPersistenceManager(), precioInicial,precioFinal);
        for ( Object tupla : tuplas)
        {
			Object [] datos = (Object []) tupla;
			String codigoBarras = (String) datos [0];;
			String nombre = (String) datos [1];
			String marca = (String) datos [2];
			String unidadMedida = (String) datos [3];
			String presentacion = (String) datos [4];
			int cantidadPresentacion = ((BigDecimal) datos [5]).intValue ();
			int volumenEmpaque = ((BigDecimal) datos [6]).intValue ();
			int pesoEmpaque = ((BigDecimal) datos [7]).intValue ();
			String tipo = (String) datos [8];
			String categoria = (String) datos [9];
			Timestamp fechaVencimiento = (Timestamp) datos [10];
			int precioVenta= ((BigDecimal) datos [11]).intValue ();
			Object [] resp = new Object [2];
			resp [0] = new Producto(codigoBarras,nombre,marca,unidadMedida,presentacion,cantidadPresentacion,volumenEmpaque,pesoEmpaque,tipo,categoria,fechaVencimiento);
			resp [1] = precioVenta;	
			
			respuesta.add(resp);
        }

		return respuesta;
	}
	public List<Producto> darProductosPesoUnaSucursal(long peso,long idSucursal)
	{
		List<Producto> respuesta = new LinkedList <Producto> ();
		List<Object> tuplas = sqlProducto.darProductosPesoUnaSucursal (pmf.getPersistenceManager(), peso, idSucursal);
        for ( Object tupla : tuplas)
        {
			Object [] datos = (Object []) tupla;
			String codigoBarras = (String) datos [0];;
			String nombre = (String) datos [1];
			String marca = (String) datos [2];
			String unidadMedida = (String) datos [3];
			String presentacion = (String) datos [4];
			int cantidadPresentacion = ((BigDecimal) datos [5]).intValue ();
			int volumenEmpaque = ((BigDecimal) datos [6]).intValue ();
			int pesoEmpaque = ((BigDecimal) datos [7]).intValue ();
			String tipo = (String) datos [8];
			String categoria = (String) datos [9];
			Timestamp fechaVencimiento = (Timestamp) datos [10];

			
			Producto resp = new Producto(codigoBarras,nombre,marca,unidadMedida,presentacion,cantidadPresentacion,volumenEmpaque,pesoEmpaque,tipo,categoria,fechaVencimiento);

			
			respuesta.add(resp);
        }

		return respuesta;
	}
	public List<Producto> darProductosPesoTodasSucursales (long peso)
	{
		List<Producto> respuesta = new LinkedList <Producto> ();
		List<Object> tuplas = sqlProducto.darProductosPesoTodasSucursales (pmf.getPersistenceManager(), peso);
        for ( Object tupla : tuplas)
        {
			Object [] datos = (Object []) tupla;
			String codigoBarras = (String) datos [0];;
			String nombre = (String) datos [1];
			String marca = (String) datos [2];
			String unidadMedida = (String) datos [3];
			String presentacion = (String) datos [4];
			int cantidadPresentacion = ((BigDecimal) datos [5]).intValue ();
			int volumenEmpaque = ((BigDecimal) datos [6]).intValue ();
			int pesoEmpaque = ((BigDecimal) datos [7]).intValue ();
			String tipo = (String) datos [8];
			String categoria = (String) datos [9];
			Timestamp fechaVencimiento = (Timestamp) datos [10];

			
			Producto resp = new Producto(codigoBarras,nombre,marca,unidadMedida,presentacion,cantidadPresentacion,volumenEmpaque,pesoEmpaque,tipo,categoria,fechaVencimiento);

			
			respuesta.add(resp);
        }

		return respuesta;
	}
	public List<Producto> darProductosTipoUnaSucursal (String tipo,long idSucursal)
	{
		List<Producto> respuesta = new LinkedList <Producto> ();
		List<Object> tuplas = sqlProducto.darProductosTipoUnaSucursal (pmf.getPersistenceManager(), tipo, idSucursal);
        for ( Object tupla : tuplas)
        {
			Object [] datos = (Object []) tupla;
			String codigoBarras = (String) datos [0];;
			String nombre = (String) datos [1];
			String marca = (String) datos [2];
			String unidadMedida = (String) datos [3];
			String presentacion = (String) datos [4];
			int cantidadPresentacion = ((BigDecimal) datos [5]).intValue ();
			int volumenEmpaque = ((BigDecimal) datos [6]).intValue ();
			int pesoEmpaque = ((BigDecimal) datos [7]).intValue ();
			String tipo1 = (String) datos [8];
			String categoria = (String) datos [9];
			Timestamp fechaVencimiento = (Timestamp) datos [10];

			
			Producto resp = new Producto(codigoBarras,nombre,marca,unidadMedida,presentacion,cantidadPresentacion,volumenEmpaque,pesoEmpaque,tipo1,categoria,fechaVencimiento);

			
			respuesta.add(resp);
        }

		return respuesta;
	}
	public List<Producto> darProductosTipoTodasSucursales (String tipo)
	{
		List<Producto> respuesta = new LinkedList <Producto> ();
		List<Object> tuplas = sqlProducto.darProductosTipoTodasSucursales (pmf.getPersistenceManager(), tipo);
        for ( Object tupla : tuplas)
        {
			Object [] datos = (Object []) tupla;
			String codigoBarras = (String) datos [0];;
			String nombre = (String) datos [1];
			String marca = (String) datos [2];
			String unidadMedida = (String) datos [3];
			String presentacion = (String) datos [4];
			int cantidadPresentacion = ((BigDecimal) datos [5]).intValue ();
			int volumenEmpaque = ((BigDecimal) datos [6]).intValue ();
			int pesoEmpaque = ((BigDecimal) datos [7]).intValue ();
			String tipo1 = (String) datos [8];
			String categoria = (String) datos [9];
			Timestamp fechaVencimiento = (Timestamp) datos [10];

			
			Producto resp = new Producto(codigoBarras,nombre,marca,unidadMedida,presentacion,cantidadPresentacion,volumenEmpaque,pesoEmpaque,tipo1,categoria,fechaVencimiento);

			
			respuesta.add(resp);
        }

		return respuesta;
	}
	
	
	public List<Object []> darProductosXUnidadesUnaSucursal(String fechaInicial, String fechaFinal,long idSucursal, long unidades)
	{
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object> tuplas = sqlProducto.darProductosXUnidadesUnaSucursal(pmf.getPersistenceManager(), fechaInicial,fechaFinal,idSucursal,unidades);
        for ( Object tupla : tuplas)
        {
			Object [] datos = (Object []) tupla;
			String codigoBarras = (String) datos [0];;
			String nombre = (String) datos [1];
			String marca = (String) datos [2];
			String unidadMedida = (String) datos [3];
			String presentacion = (String) datos [4];
			int cantidadPresentacion = ((BigDecimal) datos [5]).intValue ();
			int volumenEmpaque = ((BigDecimal) datos [6]).intValue ();
			int pesoEmpaque = ((BigDecimal) datos [7]).intValue ();
			String tipo = (String) datos [8];
			String categoria = (String) datos [9];
			Timestamp fechaVencimiento = (Timestamp) datos [10];
			int unidades1= ((BigDecimal) datos [11]).intValue ();
			

			Object [] resp = new Object [2];
			resp [0] = new Producto(codigoBarras,nombre,marca,unidadMedida,presentacion,cantidadPresentacion,volumenEmpaque,pesoEmpaque,tipo,categoria,fechaVencimiento);
			resp [1] = unidades1;	
			
			respuesta.add(resp);
        }

		return respuesta;
	}
	public List<Object []> darProductosXUnidadesTodasSucursales(String fechaInicial, String fechaFinal,long unidades)
	{
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object> tuplas = sqlProducto.darProductosXUnidadesTodasSucursales(pmf.getPersistenceManager(), fechaInicial,fechaFinal,unidades);
        for ( Object tupla : tuplas)
        {
			Object [] datos = (Object []) tupla;
			String codigoBarras = (String) datos [0];;
			String nombre = (String) datos [1];
			String marca = (String) datos [2];
			String unidadMedida = (String) datos [3];
			String presentacion = (String) datos [4];
			int cantidadPresentacion = ((BigDecimal) datos [5]).intValue ();
			int volumenEmpaque = ((BigDecimal) datos [6]).intValue ();
			int pesoEmpaque = ((BigDecimal) datos [7]).intValue ();
			String tipo = (String) datos [8];
			String categoria = (String) datos [9];
			Timestamp fechaVencimiento = (Timestamp) datos [10];
			int unidades1= ((BigDecimal) datos [11]).intValue ();
			

			Object [] resp = new Object [2];
			resp [0] = new Producto(codigoBarras,nombre,marca,unidadMedida,presentacion,cantidadPresentacion,volumenEmpaque,pesoEmpaque,tipo,categoria,fechaVencimiento);
			resp [1] = unidades1;	
			
			respuesta.add(resp);
        }

		return respuesta;
	}
	

	/* ****************************************************************
	 * 			Métodos para manejar VENTA_PRODUCTO
	 *****************************************************************/
	
	
	public long darDineroFechasUnaSucursal (String fechaInicio, String fechaFinal, long idSucursal)
	{
		//Retorna dinero recolectado por ventas en un rango de fechas de una sucursal
		return sqlVentaProducto.darDineroFechasUnaSucursal(pmf.getPersistenceManager(),fechaInicio,fechaFinal,idSucursal);
     
	}
	
	public List<Object []> darDineroFechasTodasSucursales (String fechaInicio, String fechaFinal)
	{
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object> tuplas = sqlVentaProducto.darDineroFechasTodasSucursales (pmf.getPersistenceManager(), fechaInicio,fechaFinal);
        for ( Object tupla : tuplas)
        {
			Object [] datos = (Object []) tupla;
			long idSucursal = ((BigDecimal) datos [0]).longValue ();
			String nombreSucursal = (String) datos [1];
			String telefonoSucursal = (String) datos [2];
			String direccionSucursal = (String) datos [3];
			String ciudadSucursal = (String) datos [4];
			int dineroRecolectado = ((BigDecimal) datos [5]).intValue ();

			Object [] resp = new Object [2];
			resp [0] = new Sucursal(idSucursal, nombreSucursal, telefonoSucursal, direccionSucursal,ciudadSucursal);
			resp [1] = dineroRecolectado;	
			
			respuesta.add(resp);
        }

		return respuesta;
	}
	
	public List<VentaProducto> darVentasClienteUnaSucursal (String fechaInicio, String fechaFinal, long idSucursal,long idUsuario)
	{
		List<VentaProducto> respuesta = new LinkedList <VentaProducto> ();
		List<Object> tuplas = sqlVentaProducto.darVentasClienteUnaSucursal (pmf.getPersistenceManager(), fechaInicio,fechaFinal,idSucursal,idUsuario);
        for ( Object tupla : tuplas)
        {
			Object [] datos = (Object []) tupla;
			long idCompra = ((BigDecimal) datos [0]).longValue ();
			long idUsuario1 = ((BigDecimal) datos [1]).longValue ();
			String idProducto = (String) datos [2];
			long idSucursal1 =  ((BigDecimal) datos [3]).longValue ();
			long cantidad = ((BigDecimal) datos [4]).longValue ();
			long monto = ((BigDecimal) datos [5]).longValue ();
			long puntos = ((BigDecimal) datos [6]).longValue ();
			String fecha = (String) datos [7];
			long idPromocion = ((BigDecimal) datos [8]).longValue ();

		
			VentaProducto resp = new VentaProducto(idCompra,idUsuario1,idProducto,idSucursal1,cantidad,monto,puntos,fecha,idPromocion);
	
			
			respuesta.add(resp);
        }

		return respuesta;
	}
	public List<VentaProducto> darVentasClienteTodasSucursales (String fechaInicio, String fechaFinal, long idUsuario)
	{
		List<VentaProducto> respuesta = new LinkedList <VentaProducto> ();
		List<Object> tuplas = sqlVentaProducto.darVentasClienteTodasSucursales (pmf.getPersistenceManager(), fechaInicio,fechaFinal,idUsuario);
        for ( Object tupla : tuplas)
        {
			Object [] datos = (Object []) tupla;
			long idCompra = ((BigDecimal) datos [0]).longValue ();
			long idUsuario1 = ((BigDecimal) datos [1]).longValue ();
			String idProducto = (String) datos [2];
			long idSucursal1 =  ((BigDecimal) datos [3]).longValue ();
			long cantidad = ((BigDecimal) datos [4]).longValue ();
			long monto = ((BigDecimal) datos [5]).longValue ();
			long puntos = ((BigDecimal) datos [6]).longValue ();
			String fecha = (String) datos [7];
			long idPromocion = ((BigDecimal) datos [8]).longValue ();

		
			VentaProducto resp = new VentaProducto(idCompra,idUsuario1,idProducto,idSucursal1,cantidad,monto,puntos,fecha,idPromocion);
	
			
			respuesta.add(resp);
        }

		return respuesta;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar las PROMOCIONES
	 *****************************************************************/
	
	public List<Promocion> darPromocionesPopularesSucursal(long idSucursal)
	{
		List<Promocion> respuesta = new LinkedList <Promocion> ();
		List<Object> tuplas = sqlPromocion.darPromocionesPopularesSucursal(pmf.getPersistenceManager(), idSucursal);
        for ( Object tupla : tuplas)
        {
			Object [] datos = (Object []) tupla;
			long idPromocion = ((BigDecimal) datos [0]).longValue ();
			long idSucursal1= ((BigDecimal) datos [1]).longValue ();
			Timestamp inicio = (Timestamp) datos [2];
			long duracion = ((BigDecimal) datos [3]).longValue ();
			Timestamp fin = (Timestamp) datos [4];
			long ventasMaximas = ((BigDecimal) datos [5]).longValue ();
			long ventasActuales = ((BigDecimal) datos [6]).longValue ();
			long precioPaquete = ((BigDecimal) datos [7]).longValue ();
			String idProducto = (String) datos [8];
			String descripcion= (String) datos [9];



			Promocion resp = new Promocion(idPromocion,idSucursal1,inicio,duracion,fin,ventasMaximas,ventasActuales,precioPaquete,idProducto,descripcion);
			
			respuesta.add(resp);
        }
     

		return respuesta;
	}
	public List<Promocion> darPromocionesPopularesTodasSucursales()
	{
		List<Promocion> respuesta = new LinkedList <Promocion> ();
		List<Object> tuplas = sqlPromocion.darPromocionesPopularesTodasSucursales(pmf.getPersistenceManager());
        for ( Object tupla : tuplas)
        {
			Object [] datos = (Object []) tupla;
			long idPromocion = ((BigDecimal) datos [0]).longValue ();
			long idSucursal1= ((BigDecimal) datos [1]).longValue ();
			Timestamp inicio = (Timestamp) datos [2];
			long duracion = ((BigDecimal) datos [3]).longValue ();
			Timestamp fin = (Timestamp) datos [4];
			long ventasMaximas = ((BigDecimal) datos [5]).longValue ();
			long ventasActuales = ((BigDecimal) datos [6]).longValue ();
			long precioPaquete = ((BigDecimal) datos [7]).longValue ();
			String idProducto = (String) datos [8];
			String descripcion= (String) datos [9];



			Promocion resp = new Promocion(idPromocion,idSucursal1,inicio,duracion,fin,ventasMaximas,ventasActuales,precioPaquete,idProducto,descripcion);
			
			respuesta.add(resp);
        }

		return respuesta;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar el ALMACEN
	 *****************************************************************/
	public List<Object> indiceOcupacionVolumenUnaSucursal(long idSucursal)
	{
		
		List<Object> tuplas = sqlAlmacen.indiceOcupacionVolumenUnaSucursal(pmf.getPersistenceManager(), idSucursal);
		return tuplas;
	}
	public List<Object> indiceOcupacionVolumenTodasSucursales()
	{
		
		List<Object> tuplas = sqlAlmacen.indiceOcupacionVolumenTodasSucursales(pmf.getPersistenceManager());
		return tuplas;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los PEDIDOS
	 *****************************************************************/
	public List<Pedido>  comprasProveedorUnaSucursal(long idSucursal)
	{
		List<Pedido> respuesta = new LinkedList <Pedido> ();
		List<Object> tuplas = sqlPedido. comprasProveedorUnaSucursal(pmf.getPersistenceManager(), idSucursal);
        for ( Object tupla : tuplas)
        {
        	Object [] datos = (Object []) tupla;
			long numPedido = ((BigDecimal) datos [0]).longValue ();
			String idProducto = (String) datos [1];
			String idProveedor = (String) datos [2];
			long idSucursal1 = ((BigDecimal) datos [3]).longValue ();
			long cantidadProducto =((BigDecimal) datos [4]).longValue ();
			int precioTotal = ((BigDecimal) datos [5]).intValue ();
			Timestamp inicio = (Timestamp) datos [5];
			int diasEntrega = ((BigDecimal) datos [5]).intValue ();
			String estado = (String)datos [5];
			Timestamp llegada = (Timestamp) datos [5];

			
			Pedido resp = new Pedido(numPedido,idProducto,idProveedor,idSucursal1,cantidadProducto,precioTotal,inicio,diasEntrega,estado,llegada);
			
			respuesta.add(resp);
        }
        return respuesta;
       }
	public List<Pedido>  comprasProveedorTodasSucursales()
	{
		List<Pedido> respuesta = new LinkedList <Pedido> ();
		List<Object> tuplas = sqlPedido. comprasProveedorTodasSucursales(pmf.getPersistenceManager());
        for ( Object tupla : tuplas)
        {
        	Object [] datos = (Object []) tupla;
			long numPedido = ((BigDecimal) datos [0]).longValue ();
			String idProducto = (String) datos [1];
			String idProveedor = (String) datos [2];
			long idSucursal1 = ((BigDecimal) datos [3]).longValue ();
			long cantidadProducto =((BigDecimal) datos [4]).longValue ();
			int precioTotal = ((BigDecimal) datos [5]).intValue ();
			Timestamp inicio = (Timestamp) datos [5];
			int diasEntrega = ((BigDecimal) datos [5]).intValue ();
			String estado = (String)datos [5];
			Timestamp llegada = (Timestamp) datos [5];

			
			Pedido resp = new Pedido(numPedido,idProducto,idProveedor,idSucursal1,cantidadProducto,precioTotal,inicio,diasEntrega,estado,llegada);
			
			respuesta.add(resp);
        }
        return respuesta;
       }
	/* ****************************************************************
	 * 			Métodos para administración
	 *****************************************************************/
	
	/**
	 * Elimina todas las tuplas de todas las tablas de la base de datos de Superandes
	 * Crea y ejecuta las sentencias SQL para cada tabla de la base de datos - EL ORDEN ES IMPORTANTE 
	 * @return Un arreglo con 7 números que indican el número de tuplas borradas en las tablas
	 * El orden es el mismo que el del array tablas
	 */
	public long [] limpiarSuperandes ()
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long [] resp = sqlUtil.limpiarSuperandes (pm);
            tx.commit ();
            log.info ("Borrada la base de datos");
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return new long[] {-1};
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
		
	}

	

	

	

	

	
	
	

 }
