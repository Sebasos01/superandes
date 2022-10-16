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

package uniandes.isis2304.superandes.test;

import static org.junit.Assert.fail;
import java.io.FileReader;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;
import org.junit.Test;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.isis2304.superandes.negocio.Superandes;

/**
 * Clase con métodos de prueba de conexión a la base de datos
 * @author Sebastián Ospino
 *
 */
public class ConexionTest
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(ConexionTest.class.getName());
	
	/**
	 * Ruta al archivo de configuración de los nombres de tablas de la base de datos: La unidad de persistencia existe y el esquema de la BD también
	 */
	private static final String CONFIG_TABLAS = "./src/main/resources/config/TablasBD.json"; 
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * La clase que se quiere probar
	 */
    private Superandes superandes;
	
	/* ****************************************************************
	 * 			Métodos de prueba de acceso a la BD
	 *****************************************************************/  
    /**
     * Método de prueba para acceso correcto a una base de datos
     */
    @Test
    public void normalAccessTest ()
  	{
  	  	try
		{
			log.info ("Probando el acceso a la base de datos con datos válidos (BD, credenciales, esquema");
			superandes = new Superandes (openConfig (CONFIG_TABLAS));
			log.info ("Conexión realizada correstamente");
			log.info ("Cerrando la conexión");
			
			superandes.cerrarUnidadPersistencia ();
			log.info ("Conexión cerrada");
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.info ("Prueba de acceso normal FALLÓ !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de acceso normal a la base de datos falló !! Revise persistence.xml.\n";
			msg += "Revise el log de parranderos y el de datanucleus para conocer el detalle de la excepción";
//			System.out.println (msg);
			fail (msg);
		}
  	}
  	  
	/* ****************************************************************
	 * 			Métodos de configuración
	 *****************************************************************/
    /**
     * Lee datos de configuración para la aplicación, a partir de un archivo JSON o con valores por defecto si hay errores.
     * @param tipo - El tipo de configuración deseada
     * @param archConfig - Archivo Json que contiene la configuración
     * @return Un objeto JSON con la configuración del tipo especificado
     * 			NULL si hay un error en el archivo.
     */
    private JsonObject openConfig (String archConfig)
    {
    	JsonObject config = null;
		try 
		{
			Gson gson = new Gson( );
			FileReader file = new FileReader (archConfig);
			JsonReader reader = new JsonReader ( file );
			config = gson.fromJson(reader, JsonObject.class);
			log.info ("Se encontró un archivo de configuración de tablas válido");
		} 
		catch (Exception e)
		{
//			e.printStackTrace ();
			log.info ("NO se encontró un archivo de configuración válido");			
			JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración de tablas válido: ", "TipoBebidaTest", JOptionPane.ERROR_MESSAGE);
		}	
        return config;
    }	
}
