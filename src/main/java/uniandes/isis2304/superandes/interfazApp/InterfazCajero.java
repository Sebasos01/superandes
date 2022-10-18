/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Superandes Uniandes
 * @version 1.0
 * @author Sebastián Ospino
 * @author Ana Sofía Castellanos
 * Octubre del 2022
 * 
 * Revisado por:
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.superandes.interfazApp;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.lang.reflect.Method;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.UIManager;
import uniandes.isis2304.superandes.negocio.Superandes;

/**
 * Clase para la interfaz de los cajeros
 * @author Sebastián Ospino
 * @author Ana Sofía Castellanos
 */
@SuppressWarnings("serial")
public class InterfazCajero extends InterfazGeneral {
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Ruta al archivo de configuración de la interfaz
	 */
	private static final String CONFIG_INTERFAZ = "./src/main/resources/config/interfaceCajeroConfig.json";
	
	 public InterfazCajero()
	    {
	        // Carga la configuración de la interfaz desde un archivo JSON
	        guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ);
	        
	        // Configura la apariencia del frame que contiene la interfaz gráfica
	        configurarFrame ( );
	        if (guiConfig != null) 	   
	        {
	     	   crearMenu( guiConfig.getAsJsonArray("menuBar") );
	        }
	        
	        tableConfig = openConfig ("Tablas BD", CONFIG_TABLAS);
	        superandes = new Superandes (tableConfig);
	        
	    	String path = guiConfig.get("bannerPath").getAsString();
	        panelDatos = new PanelDatos ( );

	        setLayout (new BorderLayout());
	        add (new JLabel (new ImageIcon (path)), BorderLayout.NORTH );          
	        add( panelDatos, BorderLayout.CENTER );        
	    }
	 
	 /* ****************************************************************
		 * 			CRUD de compra - Sebastián
		 *****************************************************************/
	    /**
	     * Registra la comrpa o venta de varios productos
	     * Se vende n cantidad de cada producto
	     * Incluye el proceso de descarga de inventarios
	     * Facturación
	     * Eventual generación de pedido de productos o abastecimiento en sucursal
	     * Debe considerar también el caso de las promociones
	     */
	    public void registrarCompra()
	    {
	    	try 
	    	{
	    		//
			} 
	    	catch (Exception e) 
	    	{
//				e.printStackTrace();
				String resultado = generarMensajeError(e);
				panelDatos.actualizarInterfaz(resultado);
			}
	    }
	    
	    /* ****************************************************************
		 * 			Métodos de la Interacción
		 *****************************************************************/
	    /**
	     * Método para la ejecución de los eventos que enlazan el menú con los métodos de negocio
	     * Invoca al método correspondiente según el evento recibido
	     * @param pEvento - El evento del usuario
	     */
	    @Override
		public void actionPerformed(ActionEvent pEvento)
		{
			String evento = pEvento.getActionCommand( );		
	        try 
	        {
				Method req = InterfazCajero.class.getMethod ( evento );			
				req.invoke ( this );
			} 
	        catch (Exception e) 
	        {
				e.printStackTrace();
			} 
		}
	   
		/* ****************************************************************
		 * 			Programa principal
		 *****************************************************************/
	    /**
	     * Este método ejecuta la aplicación, creando una nueva interfaz
	     * @param args Arreglo de argumentos que se recibe por línea de comandos
	     */
	    /*
	    public static void main( String[] args )
	    {
	        try
	        {
	        	
	            // Unifica la interfaz para Mac y para Windows.
	            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );
	            InterfazCajero interfaz = new InterfazCajero();
	            interfaz.setVisible( true );
	        }
	        catch( Exception e )
	        {
	            e.printStackTrace( );
	        }
	    }
	    */

}
