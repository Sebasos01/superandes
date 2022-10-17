package uniandes.isis2304.superandes.negocio;

public class Sucursal implements VOSucursal {

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador de Sucursal
	 */
	private long id;

	/**
	 * El nombre de Sucursal
	 */
	private String nombre;
	/**
	 * El telefono de Sucursal
	 */
	private String telefono;
	/**
	 * La direccion de Sucursal
	 */
	private String direccion;
	/**
	 * La ciudad de Sucursal
	 */
	private String ciudad;
	

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Único constructor
	 * @param id - El identificador de Sucursal
	 * @param nombre - El nombre de Sucursal
	 * @param telefono -El telefono de Sucursal
	 * @param direccion -La direccion de Sucursal
	 * @param ciudad -La ciudad de Sucursal
	 */
	public Sucursal(long id, String nombre, String telefono, String direccion, String ciudad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
		this.ciudad = ciudad;
	}


	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}


	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}


	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}


	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}


	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	/**
	 * @return the ciudad
	 */
	public String getCiudad() {
		return ciudad;
	}


	/**
	 * @param ciudad the ciudad to set
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}


	@Override
	public String toString() {
		return "Sucursal [id=" + id + ", nombre=" + nombre + ", telefono=" + telefono + ", direccion=" + direccion
				+ ", ciudad=" + ciudad + "]";
	}
	
	public boolean equals(Object sucursal) 
	{
		Sucursal su = (Sucursal) sucursal;
		return id == su.id && nombre.equalsIgnoreCase (su.nombre);
	}


	
	
	
}
