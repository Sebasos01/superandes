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
import uniandes.isis2304.superandes.negocio.Sucursal;
import uniandes.isis2304.superandes.negocio.Superandes;
import uniandes.isis2304.superandes.negocio.VOPedido;
import uniandes.isis2304.superandes.negocio.VOProducto;
import uniandes.isis2304.superandes.negocio.VOPromocion;
import uniandes.isis2304.superandes.negocio.VOSucursal;
import uniandes.isis2304.superandes.negocio.VOTipoUsuario;
import uniandes.isis2304.superandes.negocio.VOUsuario;
import uniandes.isis2304.superandes.negocio.VOVentaProducto;

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
	
	private static long idSucursal;
	
	 public InterfazGerenteSucursal(long idSucursal)
	    {
		 	this.idSucursal = idSucursal;
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
	    	JTextField idPedido = new JTextField();
			JTextField idProducto = new JTextField();
			JTextField idProveedor = new JTextField();
		
			JTextField cantidadProducto = new JTextField();
			JTextField precioTotal = new JTextField();
			JTextField inicio = new JTextField();
			JTextField diasEntrega = new JTextField();
			JTextField estado = new JTextField();
			JTextField llegada = new JTextField();

			Object[] message = {
					"idPedido",idPedido ,
					 "idProducto",idProducto ,
					 "idProveedor",idProveedor,
					 "cantidadProducto",cantidadProducto ,
					 "precioTotal ",precioTotal ,
					 "inicio",inicio,
					 "diasEntrega",diasEntrega,
					 "estado",estado,
					 "llegada",llegada
				
			};
			int option = JOptionPane.showConfirmDialog(null, message, "Crear Pedido", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION)
			{
		
	    		long agregadas= superandes.adicionarPedido(Integer.valueOf(idPedido.getText()),idProducto.getText(),idProveedor.getText(),idSucursal,Integer.valueOf(cantidadProducto.getText()),
	    				Integer.valueOf(precioTotal.getText()),inicio.getText(),Integer.valueOf(diasEntrega.getText()),estado.getText(),llegada.getText());
	    		if (agregadas != 0) {
	    		String resultado = "En adicionar pedido\n\n";
	    		resultado += "Pedido añadido exitosamente: ";
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);

				
				panelDatos.actualizarInterfaz(resultado);
	    		}else {
	    			String resultado = "\n No se pudo añadir a base de datos intente nuevamente";
	    			panelDatos.actualizarInterfaz(resultado);

	    			
	    		}
				
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
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
    	
    		Object[] message = {
    		    "Fecha inicio:", fechaInicio,
    		    "Fecha final:", fechaFinal
    		};
    		int option = JOptionPane.showConfirmDialog(null, message, "Consultar dinero recolectado en un rango de fechas", JOptionPane.OK_CANCEL_OPTION);
    		if (option == JOptionPane.OK_OPTION)
    		{
        		long dineroRecolectado = superandes.darDineroFechasUnaSucursal (fechaInicio.getText(), fechaFinal.getText(), idSucursal);
        	
        		
        		String resultado = "En el rango de fechas "+ String.valueOf(fechaInicio.getText())+" , "+String.valueOf(fechaFinal.getText()) + " de la sucursal " + String.valueOf(idSucursal) + ". Se recolecto un total de: "+ String.valueOf(dineroRecolectado);
        		
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
	    	JTextField fechaInicio = new JTextField();
    		JTextField fechaFinal = new JTextField();
    		JTextField idUsuario = new JTextField();
    		
    		Object[] message = {
    		    "Fecha inicio:", fechaInicio,
    		    "Fecha final:", fechaFinal,
    		    "Id usuario:", idUsuario,
    		};
    		int option = JOptionPane.showConfirmDialog(null, message, "Consultar ventas de un cliente", JOptionPane.OK_CANCEL_OPTION);
    		if (option == JOptionPane.OK_OPTION)
    		{
        		List<VOVentaProducto> dineroRecolectado = superandes.darVentasClienteUnaSucursal (fechaInicio.getText(),fechaFinal.getText(),idSucursal, Integer.parseInt(idUsuario.getText()));
        	
        		
        		String resultado = "En el rango de fechas "+ String.valueOf(fechaInicio.getText())+" , "+String.valueOf(fechaFinal.getText()) + " de la sucursal " + String.valueOf(idSucursal) + ". El cliente con id "+ String.valueOf(idUsuario.getText()) + " ha tenido estas compras:";
        		
    			resultado += "\n Operación terminada";
    			
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
	    }
	    
	    /* ****************************************************************
		 * 			Consultas Promocion - Ana
		 *****************************************************************/
	    /**
	     * Se consulta las 20 promociones que fueron vendidas en el menor tiempo
	     */
	    public void menorTiempoPromociones()
	    {
	    	
    		int option = JOptionPane.showConfirmDialog(null, "¿Desea consultar 20 promociones más populares?",null, JOptionPane.OK_CANCEL_OPTION);
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
	    	
	    	int option = JOptionPane.showConfirmDialog(null, "¿Desea consultar indice de ocupacion de los estantes?", null,JOptionPane.OK_CANCEL_OPTION);
    		if (option == JOptionPane.OK_OPTION)
    		{
        		List<Object> tuplas = superandes.indiceOcupacionVolumenUnaSucursal(idSucursal);
        		String resultado = String.format("%1$s - %2$s - %3$s - %4$s", "Id Almacen", "Tipo", "Indice Ocupación","Capacidad Volumen");
        		
        		for ( Object tupla : tuplas) {
        			
        			Object [] datos = (Object []) tupla;
        			long idAlmacen = ((BigDecimal) datos [0]).longValue ();
        			String tipo = (String) datos[1]; 
        			long indiceOcupacion = ((BigDecimal) datos [2]).longValue ();
        			long capacidadVolumen = ((BigDecimal) datos [3]).longValue ();
        			
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
	    	JTextField precioInicio = new JTextField();
    		JTextField precioFinal = new JTextField();
    	
    		Object[] message = {
    		    "Precio Inicio:", precioInicio,
    		    "Precio final:", precioFinal
    		};
    		int option = JOptionPane.showConfirmDialog(null, message, "Consultar productos en rango de precios de la sucursal", JOptionPane.OK_CANCEL_OPTION);
    		if (option == JOptionPane.OK_OPTION)
    		{
        		List<Object[]> productos = superandes.darProductosPreciosUnaSucursal (Integer.valueOf(precioInicio.getText()),Integer.valueOf(precioFinal.getText()),idSucursal);
        	
        		
        		String resultado = "En el rango de precios "+ String.valueOf(precioInicio.getText())+" , "+String.valueOf(precioFinal.getText()) + " de la sucursal " + String.valueOf(idSucursal) + ". Se encuentran los siguientes productos";
        		
        		resultado += listarProductosPrecio(productos);
    			resultado += "\n Operación terminada";
    			
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
	    
	    }
	    public void fechaVencimientoProductos()
	    {
	    	JTextField fechaVencimiento = new JTextField();
    	
    		Object[] message = {
    		    "Fecha vencimiento mínima:", fechaVencimiento,

    		};
    		int option = JOptionPane.showConfirmDialog(null, message, "Consultar productos una fecha de vencimiento mayor a  de la sucursal", JOptionPane.OK_CANCEL_OPTION);
    		if (option == JOptionPane.OK_OPTION)
    		{
        		List<VOProducto> productos = superandes.darProductosFechaVencimientoUnaSucursal (fechaVencimiento.getText(),idSucursal);
        		String resultado = "Con la fecha de vencimiento mayor a  "+ String.valueOf(fechaVencimiento.getText()) + " de la sucursal " + String.valueOf(idSucursal) + ". Se encuentran los siguientes productos";

        		for ( VOProducto p : productos) {
        			
        			resultado += "\n"+p.toString(); 	
        		}

    			resultado += "\n Operación terminada";
    			
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
	    
	    }
	    public void pesoVolProductos()
	    {
	    	JTextField peso = new JTextField();
    		
    		Object[] message = {
    		    "Peso mínimo:", peso,

    		};
    		int option = JOptionPane.showConfirmDialog(null, message, "Consultar productos con un peso mayor a de la sucursal", JOptionPane.OK_CANCEL_OPTION);
    		if (option == JOptionPane.OK_OPTION)
    		{
        		List<VOProducto> productos = superandes.darProductosPesoUnaSucursal (Integer.valueOf(peso.getText()),idSucursal);
        		String resultado = "Con peso mayor a  "+ String.valueOf(peso.getText()) + " de la sucursal " + String.valueOf(idSucursal) + ". Se encuentran los siguientes productos";

        		for ( VOProducto p : productos) {
        			
        			resultado += "\n"+p.toString(); 	
        		}

    			resultado += "\n Operación terminada";
    			
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
	    }
	
	    public void categoriaTipoProductos()
	    {
	    	JTextField tipo = new JTextField();
    		
    		Object[] message = {
    		    "Tipo:", tipo,

    		};
    		int option = JOptionPane.showConfirmDialog(null, message, "Consultar productos con un tipo de la sucursal", JOptionPane.OK_CANCEL_OPTION);
    		if (option == JOptionPane.OK_OPTION)
    		{
        		List<VOProducto> productos = superandes.darProductosTipoUnaSucursal (tipo.getText(),idSucursal);
        		String resultado = "Con un tipo:  "+ String.valueOf(tipo.getText()) + " de la sucursal " + String.valueOf(idSucursal) + ". Se encuentran los siguientes productos";

        		for ( VOProducto p : productos) {
        			
        			resultado += "\n"+p.toString(); 	
        		}

    			resultado += "\n Operación terminada";
    			
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
	    
	    }
	    public void unidadesVendidasProductos()
	    {
	    	JTextField fechaInicio = new JTextField();
    		JTextField fechaFinal = new JTextField();
    		JTextField unidades = new JTextField();
    		
    		Object[] message = {
    		    "Fecha inicial:", fechaInicio,
    		    "Fecha final:", fechaFinal,
    		    "Unidades mínimas:", unidades
    		};
    
 
    		int option = JOptionPane.showConfirmDialog(null, message, "Consultar productos con más de x unidades vendidas en un rango de fechas", JOptionPane.OK_CANCEL_OPTION);
    		if (option == JOptionPane.OK_OPTION)
    		{
        		List<Object[]> productos = superandes.darProductosXUnidadesUnaSucursal (fechaInicio.getText(),fechaInicio.getText(),idSucursal,Integer.valueOf(unidades.getText()));
        		String resultado = "En el rango de fechas "+ String.valueOf(fechaInicio.getText())+" , "+String.valueOf(fechaFinal.getText()) + " de la sucursal " + String.valueOf(idSucursal) + ". Con más de  "+ String.valueOf(unidades.getText()) + " unidades vendidas. Se tienen los siguientes productos: ";

        		
        		resultado += listarProductosUnidades(productos);

    			resultado += "\n Operación terminada";
    			
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
	    
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
	    	
    		int option = JOptionPane.showConfirmDialog(null, "¿Desea consultar compras de los proveedores?",null, JOptionPane.OK_CANCEL_OPTION);
    		if (option == JOptionPane.OK_OPTION)
    		{
        		List<VOPedido> compras = superandes.comprasProveedorUnaSucursal(idSucursal);
        		String resultado = "Las compras a proveedores de la sucursal con id: " +String.valueOf(idSucursal)+"\n" ;
        		
        		for ( VOPedido com : compras) {
        			
        			resultado += "\n"+com.toString(); 	
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
	    private String listarProductosPrecio (List<Object[]> lista) 
	    {
	    	String resp = "";
	    	int i = 1;
	        for (Object [] tupla : lista)
	        {
				VOProducto suc = (VOProducto) tupla [0];
				int precio = (int) tupla [1];
		        String resp1 = i++ + ". " + "[";
				resp1 += suc + ", ";
				resp1 += "Precio Venta: " + precio;
		        resp1 += "]";
		        resp += resp1 + "\n";
	        }
	        return resp;
		}
	    private String listarProductosUnidades(List<Object[]> lista) 
	    {
	    	String resp = "";
	    	int i = 1;
	        for (Object [] tupla : lista)
	        {
				VOProducto suc = (VOProducto) tupla [0];
				int unidades = (int) tupla [1];
		        String resp1 = i++ + ". " + "[";
				resp1 += suc + ", ";
				resp1 += "Unidades: " + unidades;
		        resp1 += "]";
		        resp += resp1 + "\n";
	        }
	        return resp;
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
	            InterfazGerenteSucursal interfaz = new InterfazGerenteSucursal(2 );
	            interfaz.setVisible( true );
	        }
	        catch( Exception e )
	        {
	            e.printStackTrace( );
	        }
	    }

	

}
