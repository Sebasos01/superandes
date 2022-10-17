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
import java.math.BigDecimal;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import uniandes.isis2304.superandes.negocio.Promocion;
import uniandes.isis2304.superandes.negocio.Superandes;
import uniandes.isis2304.superandes.negocio.VOPromocion;
import uniandes.isis2304.superandes.negocio.VOTipoUsuario;

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
	    public void fechasDineroVentas()
	    {
	    	
    		JTextField fechaInicio = new JTextField();
    		JTextField fechaFinal = new JTextField();
    		long idSucursal = 1;//-> Cambiar el 3 por lo del login
    		//OJO Falta lo del id Sucursal ->Se obtienen del login
    		Object[] message = {
    		    "Fecha inicio:", fechaInicio,
    		    "Fecha final:", fechaFinal
    		};
    		int option = JOptionPane.showConfirmDialog(null, message, "Consultar dinero recolectado en un rango de fechas", JOptionPane.OK_CANCEL_OPTION);
    		if (option == JOptionPane.OK_OPTION)
    		{
        		long dineroRecolectado = superandes.darDineroFechasUnaSucursal (fechaInicio.getText(), fechaFinal.getText(), idSucursal);
        	
        		
        		String resultado = "En el rango de fechas "+ String.valueOf(fechaInicio.getText())+" , "+String.valueOf(fechaFinal.getText()) + "de la sucursal " + String.valueOf(idSucursal) + "Se recolecto un total de: "+ String.valueOf(dineroRecolectado);
        		
    			resultado += "\n Operación terminada";
    			
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
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
	    	long idSucursal = 1;//-> Cambiar el 3 por lo del login
    		int option = JOptionPane.showConfirmDialog(null, null,"¿Desea consultar 20 promociones más populares?", JOptionPane.OK_CANCEL_OPTION);
    		if (option == JOptionPane.OK_OPTION)
    		{
        		List<VOPromocion> promociones = superandes.darPromocionesPopularesSucursal(idSucursal);
        		String resultado = "Las 20 promociones más populares en la sucursal con id: " +String.valueOf(idSucursal)+"\n" ;
        		
        		for ( VOPromocion prom : promociones) {
        			
        			resultado += "\n"+prom.toString(); 	
        		}

    			resultado += "\n Operación terminada";
    			
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
	    }
	    /* ****************************************************************
		 * 			Consultas Estante bodega - Ana
		 *****************************************************************/
	    /**
	     * Se consulta indice de ocupacion de las bodegas y estante por peso y volumen
	     */
	    public void volumenOcupacion()
	    {
	    	long idSucursal = 1;
	    	int option = JOptionPane.showConfirmDialog(null, "¿Desea consultar indice de ocupacion de los estantes?", null,JOptionPane.OK_CANCEL_OPTION);
    		if (option == JOptionPane.OK_OPTION)
    		{
        		List<Object> tuplas = superandes.indiceOcupacionVolumenUnaSucursal(idSucursal);
        		String resultado = String.format("%1$s - %2$s - %3$s - %4$s", "Id Almacen", "Tipo", "Indice Ocupación","Capacidad Volumen");
        		
        		for ( Object tupla : tuplas) {
        			
        			Object [] datos = (Object []) tupla;
        			long idAlmacen = ((BigDecimal) datos [0]).longValue ();
        			String tipo = (String) datos[1]; 
        			long indiceOcupacion = ((BigDecimal) datos [0]).longValue ();
        			long capacidadVolumen = ((BigDecimal) datos [0]).longValue ();
        			
        			resultado += "\n"+String.format("%1$s - %2$s - %3$s - %4$s", 
                           String.valueOf(idAlmacen), tipo, String.valueOf(indiceOcupacion),String.valueOf(capacidadVolumen));	
        		}

    			resultado += "\n Operación terminada";
    			
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
	    
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
