package uniandes.isis2304.superandes.negocio;
/**
 * Interfaz para los métodos get de Sucursal
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Sebastián Ospino
 * @author Ana Sofía Castellanos
 */
public interface VOSucursal {
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * @return El id de Sucursal
	 */
	public long getId();

	/**
	 * @return El nombre de Sucursal
	 */
	public String getNombre();
	
	/**
	 * @return El telefono de Sucursal
	 */
	public String getTelefono();
	
	/**
	 * @return La direccion de Sucursal
	 */
	public String getDireccion();
	
	/**
	 * @return La ciudad de Sucursal
	 */
	public String getCiudad();

	/**
	 * @return Una cadena de caracteres con la información de Sucursal
	 */
	@Override
	public String toString(); 

	/**
	 * Define la igualdad dos Sucursales
	 * @param su - La sucursal a comparar
	 * @return true si tienen el mismo identificador y el mismo nombre
	 */
	@Override
	public boolean equals (Object su); 

}
