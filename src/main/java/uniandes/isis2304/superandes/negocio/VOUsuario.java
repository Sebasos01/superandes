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

package uniandes.isis2304.superandes.negocio;

/**
 * Clase para modelar el el VO de usuario
 * @author Sebastián Ospino
 */
public interface VOUsuario {
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * @return el código del usuario
	 */
	public long getCodigo_usuario();

	/**
	 * @return el documento del usuario
	 */
	public String getDocumento();



	/**
	 * @return el tipo de documento del usuario
	 */
	public String getTipo_documento();


	/**
	 * @return el nombre del usuario
	 */
	public String getNombre();


	/**
	 * @return el email del usuario 
	 */
	public String getEmail();

	/**
	 * @return la contraseña del usuario
	 */
	public String getContrasena();


	/**
	 * @return el id del tipo de usuario
	 */
	public long getId_tipo();
	
	/**
	 * @return el id de la sucursal
	 */
	public long getId_sucursal();
	/**
	 * @return Una cadena de caracteres con la información del tipo de usuario
	 */
	@Override
	public String toString(); 

}
