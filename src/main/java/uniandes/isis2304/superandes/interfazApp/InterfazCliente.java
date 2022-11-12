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
import java.awt.Choice;
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
import uniandes.isis2304.superandes.negocio.VOSucursal;
import uniandes.isis2304.superandes.negocio.VOTipoUsuario;
import uniandes.isis2304.superandes.negocio.VOUsuario;
import uniandes.isis2304.superandes.negocio.VOVentaProducto;

/**
 * Clase principal de la interfaz
 * @author Sebastián Ospino
 */
@SuppressWarnings("serial")
public class InterfazCliente extends InterfazGeneral
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Ruta al archivo de configuración de la interfaz
	 */
	private static final String CONFIG_INTERFAZ = "./src/main/resources/config/interfaceClienteConfig.json"; 
	

    public InterfazCliente( )
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
    		if (nombreTipo != null)
    		{
    			String resultado;
    			try 
        		{
    				VOTipoUsuario tu = superandes.adicionarTipoUsuario (nombreTipo);
    				resultado = "En adicionarTipoUsuario\n\n";
            		resultado += "Tipo de usuario adicionado exitosamente: " + tu;
        			resultado += "\n Operación terminada";
    			}
    			catch(Exception e) 
        		
        		{
    				resultado = generarMensajeError(e);
        			//throw new Exception ("No se pudo crear un tipo de usuario con nombre: " + nombreTipo);
        		}

    			
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
		int option = JOptionPane.showConfirmDialog(null, message, "Crear sucursal", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION)
		{
	
    		VOSucursal su= superandes.adicionarSucursal(nombre.getText(), telefono.getText(),direccion.getText(),ciudad.getText());
    		if (su != null) {
    		String resultado = "En adicionar sucursal\n\n";
    		resultado += "Sucursal añadida exitosamente: ";
    		resultado +=su;
			resultado += "\n Operación terminada";
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
    JTextField documento = new JTextField();
    Choice tipo_documento = new Choice();
    tipo_documento.setBounds(100, 100, 75, 75); 
    tipo_documento.add("CC");
    tipo_documento.add("CE");
    tipo_documento.add("NIT");
    tipo_documento.add("TI");
    
	JTextField nombre = new JTextField();
	JTextField email = new JTextField();
	JTextField contrasena= new JTextField();
	JTextField id_tipo = new JTextField();
	JTextField puntos = new JTextField();
	JTextField direccion = new JTextField();
	JTextField ciudad = new JTextField();
	JTextField id_sucursal = new JTextField();
	
	//OJO Falta lo del id Sucursal ->Se obtienen del login
	Object[] message = {
	    "Documento:", documento,
	    "Tipo documento:", tipo_documento,
	    "Nombre:", nombre,
	    "Email:", email,
	    "Contraseña:", contrasena,
	    "Id_tipo:", id_tipo,
	    "Puntos:", puntos,
	    "Direccion:", direccion,
	    "Ciudad:", ciudad,
	    "Id sucursal:", id_sucursal,
	};
	int option = JOptionPane.showConfirmDialog(null, message, "Crear usuario", JOptionPane.OK_CANCEL_OPTION);
	if (option == JOptionPane.OK_OPTION)
	{

		VOUsuario us= superandes.registrarUsuario(documento.getText(), tipo_documento.getSelectedItem(),nombre.getText(),
				email.getText(),contrasena.getText(),Integer.parseInt(id_tipo.getText()),Integer.parseInt(puntos.getText()),direccion.getText(),ciudad.getText(),Integer.parseInt(id_sucursal.getText()));
		if (us != null) {
		String resultado = "En adicionar usuario\n\n";
		resultado += "Usuario añadida exitosamente: ";
		resultado +=us;
		resultado += "\n Operación terminada";
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
			Method req = InterfazCliente.class.getMethod ( evento );			
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
            InterfazCliente interfaz = new InterfazCliente( );
            interfaz.setVisible( true );
        }
        catch( Exception e )
        {
            e.printStackTrace( );
        }
    }
    
}
