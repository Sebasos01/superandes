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
 * Clase de la interfaz para el gerente del supermercado
 * @author Sebastián Ospino
 * @author Ana Sofía Castellanos
 */
@SuppressWarnings("serial")
public class InterfazGerenteSucursal extends InterfazGeneral {
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Ruta al archivo de configuración de la interfaz
	 */
	private static final String CONFIG_INTERFAZ = "./src/main/resources/config/interfaceGerenteSucursalConfig.json";
	
	 public InterfazGerenteSucursal( )
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
		 * 			CRUD de Promocion - Sebastián
		 *****************************************************************/
	    /**
	     * Registra una promoción en base a la información dada por el usuario
	     * Se crea una nueva tupla de PROMOCION en la base de datos
	     */
	    public void registrarPromocion()
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
		 * 			CRUD de Pedido - Sebastián
		 *****************************************************************/
	    /**
	     * Registra una promoción en base a la información dada por el usuario
	     * Se crea una nueva tupla de PROMOCION en la base de datos
	     */
	    public void registrarPedido()
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
		 * 			Consultas Venta - Ana
		 *****************************************************************/
	    /**
	     * Se consulta el dinero recolectados por ventas en un rango de fechas
	     * Se consulta dinero recolectado en un año
	     * Se consulta las ventas de un cliente 
	     */
	    public void fechaDineroVentas()
	    {
	    
	    }
	    public void anioDineroVentas()
	    {
	    
	    }
	    public void clienteVentas()
	    {
	    
	    }
	    
	    /* ****************************************************************
		 * 			Consultas Promocion - Ana
		 *****************************************************************/
	    /**
	     * Se consulta las 20 promociones que fueron vendidas en el menor tiempo
	     */
	    public void menorTiempoPromociones()
	    {
	    
	    }
	    /* ****************************************************************
		 * 			Consultas Estante bodega - Ana
		 *****************************************************************/
	    /**
	     * Se consulta indice de ocupacion de las bodegas y estante por peso y volumen
	     */
	    public void pesoOcupacion()
	    {
	    
	    }
	    public void volumenOcupacion()
	    {
	    
	    }
	    /* ****************************************************************
		 * 			Consultas Producto - Ana
		 *****************************************************************/
	    /**
	     * Se consultan productos en un rango de precios
	     * Se consultan productoas con una fecha de vencimiento posterior a una dada
	     * Se consultan productos cuyo peo / volumen esten en un rango
	     * Se consultan productos que vienen de un proveedor dado
	     * Se consultan productos disponibles en una sucursal dada
	     * Se consultan productos disponibles en una ciudad dada
	     * Se consultan productos de una categoria/tipo
	     * Se consultan productos con X unidades vendidas en un rango de fechas
	     */
	    public void precioProductos()
	    {
	    
	    }
	    public void fechaVencimientoProductos()
	    {
	    
	    }
	    public void pesoVolProductos()
	    {
	    
	    }
	    public void proveedorProductos()
	    {
	    
	    }
	    public void categoriaTipoProductos()
	    {
	    
	    }
	    public void unidadesVendidasProductos()
	    {
	    
	    }
	    /* ****************************************************************
		 * 			Consultas Pedido- Ana
		 *****************************************************************/
	    /**
	     * Se consultan compras realizadas a proveedores y los proveedores
	     */
	    public void proveedorSuperandes()
	    {
	    
	    }
	    public void comprasSuperandes()
	    {
	    
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
				Method req = InterfazGerenteSucursal.class.getMethod ( evento );			
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
	    public static void main( String[] args )
	    {
	        try
	        {
	        	
	            // Unifica la interfaz para Mac y para Windows.
	            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );
	            InterfazGerenteSucursal interfaz = new InterfazGerenteSucursal( );
	            interfaz.setVisible( true );
	        }
	        catch( Exception e )
	        {
	            e.printStackTrace( );
	        }
	    }

}
