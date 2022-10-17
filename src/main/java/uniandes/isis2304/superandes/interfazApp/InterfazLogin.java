package uniandes.isis2304.superandes.interfazApp;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class InterfazLogin extends JFrame {

	private Object[] login() {
		Object[] respuestas = new Object[3];
		JTextField email = new JTextField();
		JPasswordField contrasena = new JPasswordField();
		Object[] message = {
		    "Email:", email,
		    "Contraseña:", contrasena
		};
		int option = JOptionPane.showConfirmDialog(null, message, "Por favor ingrese sus credenciales", JOptionPane.OK_CANCEL_OPTION);
		respuestas[0] = email.getText();
		respuestas[1] = String.valueOf(contrasena.getPassword());
		respuestas[2] = option;
		return respuestas;
	}
	
	private void inicializarInterfaz(String email, String contrasena) {
		
	}
	
	public static void main(String[] args) {
	  try {	
	    // Unifica el login
	    UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );
	    InterfazLogin interfaz = new InterfazLogin( );
	    Object[] respuestas = interfaz.login();
	    if ((int) respuestas[2] == 0) {
	    	// Implementar loq ue falta del login
	    }
      } catch( Exception e ) {
        e.printStackTrace( );
        }
	}
	
}
