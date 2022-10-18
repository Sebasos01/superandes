package uniandes.isis2304.superandes.interfazApp;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import uniandes.isis2304.superandes.negocio.Superandes;
import uniandes.isis2304.superandes.negocio.VOUsuario;

@SuppressWarnings("serial")
public class InterfazLogin extends InterfazGeneral {
	
	protected static Superandes superandes;
	
	private static final String CONFIG_INTERFAZ = "./src/main/resources/config/interfaceGerenteSucursalConfig.json";
	
	public InterfazLogin() 
    {

        tableConfig = openConfig ("Tablas BD", CONFIG_TABLAS);
        superandes = new Superandes (tableConfig);
        Object[] respuestas = login();
        VOUsuario usuario = superandes.loginUsuario(respuestas[0].toString(), respuestas[1].toString());
		
		if (usuario ==null) {
			JOptionPane.showMessageDialog(null, "Sus credenciales son invalidas.Intente de nuevo.");
		}else {
			String tipo = superandes.darNombreTipo(usuario.getId_tipo());
			if (tipo.equals("ADMIN_DATOS")){
				InterfazAdminDatos interfaz = new InterfazAdminDatos( );
	            interfaz.setVisible( true );
			} else if (tipo.equals("GERENTE_SUCURSAL")){
				InterfazGerenteSucursal interfaz = new InterfazGerenteSucursal(usuario.getId_sucursal() );
	            interfaz.setVisible( true );
			}else if (tipo.equals("GERENTE_SUPERMERCADO")){
				InterfazGerenteSupermercado interfaz = new InterfazGerenteSupermercado( );
	            interfaz.setVisible( true );
			}else if (tipo.equals("OPERADOR")){
				InterfazOperador interfaz = new InterfazOperador( );
	            interfaz.setVisible( true );
			}else if (tipo.equals("CAJERO")){
				InterfazCajero interfaz = new InterfazCajero( );
	            interfaz.setVisible( true );
			}else {
				JOptionPane.showMessageDialog(null, "No existe una iterfaz asociada a ese tipo de usuario.");
			}
					
		}
           
    }
	

	private static String[] login() {
		String[] respuestas = new String[2];
		JTextField email = new JTextField();
		JPasswordField contrasena = new JPasswordField();
		Object[] message = {
		    "Email:", email,
		    "Contraseña:", contrasena
		};
		String[] options = {"OK"};
		int option = JOptionPane.showOptionDialog(null, message, "Por favor ingrese sus credenciales",JOptionPane.NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, options , options[0]);
		if (option == JOptionPane.OK_OPTION) {
			
		respuestas[0] = email.getText();
		respuestas[1] = String.valueOf(contrasena.getPassword());

		}else {
			respuestas = null;
		}
		return respuestas;
	}
	
	private static void inicializarInterfaz(String email, String contrasena) {
		
		
		
		
	}
	
	public static void main(String[] args) {
	  try {	
	    // Unifica el login
	    UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );
	    InterfazLogin interfaz = new InterfazLogin( );

	    /*Object[] respuestas = interfaz.login();
		
		inicializarInterfaz((String) respuestas[0], (String)respuestas[1]);*/

      } catch( Exception e ) {
        e.printStackTrace( );
        }
	}


	@Override
	public void actionPerformed(ActionEvent pEvento) {
		// TODO Auto-generated method stub
		
	}
	
}
