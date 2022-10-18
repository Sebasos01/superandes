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

package uniandes.isis2304.superandes.interfazApp;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.lang.reflect.Method;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import uniandes.isis2304.superandes.negocio.Sucursal;
import uniandes.isis2304.superandes.negocio.Superandes;
import uniandes.isis2304.superandes.negocio.VOTipoUsuario;
import uniandes.isis2304.superandes.negocio.VOVentaProducto;

/**
 * Clase principal de la interfaz
 * @author Sebastián Ospino
 */
@SuppressWarnings("serial")
public class InterfazAdminDatos extends InterfazGeneral
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Ruta al archivo de configuración de la interfaz
	 */
	private static final String CONFIG_INTERFAZ = "./src/main/resources/config/interfaceAdminDatosConfig.json"; 
	

    public InterfazAdminDatos( )
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
	 * 			CRUD de TipoUsuario
	 *****************************************************************/
    /**
     * Adiciona un tipo de usuario con la información dada por el usuario
     * Se crea una nueva tupla de TIPO_USUARIO en la base de datos, si un tipo de bebida con ese nombre no existía
     */
    public void adicionarTipoUsuario()
    {
    	try 
    	{
    		String nombreTipo = JOptionPane.showInputDialog (this, "Ingrese el nombre del tipo de usuario", "Adicionar tipo de usuario", JOptionPane.QUESTION_MESSAGE);
    		String esCliente = JOptionPane.showInputDialog (this, "¿Es cliente? (Y/N)", "Adicionar tipo de usuario", JOptionPane.QUESTION_MESSAGE);
    		if (nombreTipo != null)
    		{
        		VOTipoUsuario tu = superandes.adicionarTipoUsuario (nombreTipo, esCliente);
        		if (tu == null)
        		{
        			throw new Exception ("No se pudo crear un tipo de usuario con nombre: " + nombreTipo);
        		}
        		String resultado = "En adicionarTipoUsuario\n\n";
        		resultado += "Tipo de usuario adicionado exitosamente: " + tu;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
    public void obtenerListaTipoUsuario()
    {
    	try 
    	{
			List <VOTipoUsuario> lista = superandes.obtenerListaTipoUsuario();
			String resultado = "En listar tipo usuario";
			resultado += lista.stream()
						.map(tu -> tu.toString())
						.reduce("", (acc, tu) -> acc + "\n" + tu);
			resultado += "\n Operación terminada";
			panelDatos.actualizarInterfaz(resultado);
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    /* ****************************************************************
	 * 			CRUD de Sucursal- Ana
	 *****************************************************************/
    /**
     * Adiciona una sucursal a la base de datos
     */
    public void adicionarSucursal()
    {
    	JTextField nombre = new JTextField();
		JTextField telefono = new JTextField();
		JTextField direccion = new JTextField();
		JTextField ciudad = new JTextField();
		long idSucursal = 1;//-> Cambiar el 3 por lo del login
		//OJO Falta lo del id Sucursal ->Se obtienen del login
		Object[] message = {
		    "Nombre:", nombre,
		    "Telefono:", telefono,
		    "Direccion:", direccion,
		    "Ciudad:", ciudad,
		};
		int option = JOptionPane.showConfirmDialog(null, message, "Consultar ventas de un cliente", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION)
		{
	
    		Sucursal su= superandes.adicionarSucursal(nombre.getText(), telefono.getText(),direccion.getText(),ciudad.getText());
    		if (su != null) {
    		String resultado = "En adicionar sucursal\n\n";
    		resultado += "Sucursal añadida exitosamente: ";
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
	 * 			CRUD de Usuarios
	 *****************************************************************/
    /**
     * Adiciona un usuario con un tipo de usuario previamente existente
     */
    public void adicionarUsuario()
    {
    	
    }
    
    /* ****************************************************************
	 * 			CRUD de Bodega
	 *****************************************************************/
    /**
     * Adiciona una bodega a la base de datos
     */
    public void adicionarBodega()
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
			Method req = InterfazAdminDatos.class.getMethod ( evento );			
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
            InterfazAdminDatos interfaz = new InterfazAdminDatos( );
            interfaz.setVisible( true );
        }
        catch( Exception e )
        {
            e.printStackTrace( );
        }
    }
    */
   
}
