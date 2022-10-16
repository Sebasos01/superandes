package uniandes.isis2304.superandes.interfazApp;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.lang.reflect.Method;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.UIManager;

import uniandes.isis2304.superandes.negocio.Superandes;

/**
 * Clase interfaz para gerente de supermercado
 * @author Sebastián Ospino
 * @author Ana Sofía Castellanos
 */
@SuppressWarnings("serial")
public class InterfazGerenteSupermercado extends InterfazGeneral{

	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Ruta al archivo de configuración de la interfaz
	 */
	private static final String CONFIG_INTERFAZ = "./src/main/resources/config/interfaceGerenteSupermercadoConfig.json";
	
	 public InterfazGerenteSupermercado( )
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
	    public void sucursalProductos()
	    {
	    
	    }
	    public void ciudadProductos()
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
	    
	    @Override
		public void actionPerformed(ActionEvent pEvento)
		{
			String evento = pEvento.getActionCommand( );		
	        try 
	        {
				Method req = InterfazGerenteSupermercado.class.getMethod ( evento );			
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
	            InterfazGerenteSupermercado interfaz = new InterfazGerenteSupermercado( );
	            interfaz.setVisible( true );
	        }
	        catch( Exception e )
	        {
	            e.printStackTrace( );
	        }
	    }
	
}
