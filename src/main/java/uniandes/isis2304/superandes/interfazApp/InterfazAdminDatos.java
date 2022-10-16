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

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import uniandes.isis2304.superandes.negocio.Superandes;
import uniandes.isis2304.superandes.negocio.VOTipoUsuario;

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
    		if (nombreTipo != null)
    		{
        		VOTipoUsuario tu = superandes.adicionarTipoUsuario (nombreTipo);
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
            InterfazAdminDatos interfaz = new InterfazAdminDatos( );
            interfaz.setVisible( true );
        }
        catch( Exception e )
        {
            e.printStackTrace( );
        }
    }
   
}
