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
 * Clase para modelar el concepto Usuario
 * @author Sebastián Ospino
 */
public class Usuario implements VOUsuario {
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador del tipo de usuario
	 */
	private long codigo_usuario;
	
	/**
	 * El documento de identidad
	 */
	private String documento;
	
	/**
	 * El tipo de doc de identidad
	 */
	private String tipo_documento;
	
	/**
	 * El nombre completo del usuario
	 */
	private String nombre;
	
	/**
	 * El correo electronico del usuario
	 */
	private String email;
	
	/**
	 * La contraseña del usuario
	 */
	private String contrasena;
	
	/**
	 * El id del tipo de usuario
	 */
	private long id_tipo;
	
	private long puntos;
	private String direccion;
	private String ciudad;
	
	/**
	 * El id de la sucursal en caso de tener
	 */
	private long id_sucursal;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor sin parámetros, sirve para mapear de la base de datos
	 * a objetos
	 */
	public Usuario() {}
	

	public Usuario(long codigo_usuario, String documento, String tipo_documento, String nombre, String email,
			String contrasena, long id_tipo, long puntos, String direccion, String ciudad, long id_sucursal) {
		super();
		this.codigo_usuario = codigo_usuario;
		this.documento = documento;
		this.tipo_documento = tipo_documento;
		this.nombre = nombre;
		this.email = email;
		this.contrasena = contrasena;
		this.id_tipo = id_tipo;
		this.puntos = puntos;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.id_sucursal = id_sucursal;
	}


	/**
	 * @return the codigo_usuario
	 */
	public long getCodigo_usuario() {
		return codigo_usuario;
	}

	/**
	 * @param codigo_usuario the codigo_usuario to set
	 */
	public void setCodigo_usuario(long codigo_usuario) {
		this.codigo_usuario = codigo_usuario;
	}

	/**
	 * @return the documento
	 */
	public String getDocumento() {
		return documento;
	}

	/**
	 * @param documento the documento to set
	 */
	public void setDocumento(String documento) {
		this.documento = documento;
	}

	/**
	 * @return the tipo_documento
	 */
	public String getTipo_documento() {
		return tipo_documento;
	}

	/**
	 * @param tipo_documento the tipo_documento to set
	 */
	public void setTipo_documento(String tipo_documento) {
		this.tipo_documento = tipo_documento;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the contrasena
	 */
	public String getContrasena() {
		return contrasena;
	}

	/**
	 * @param contrasena the contrasena to set
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	/**
	 * @return the id_tipo
	 */
	public long getId_tipo() {
		return id_tipo;
	}

	/**
	 * @param id_tipo the id_tipo to set
	 */
	public void setId_tipo(long id_tipo) {
		this.id_tipo = id_tipo;
	}

	/**
	 * @return the puntos
	 */
	public long getPuntos() {
		return puntos;
	}

	/**
	 * @param puntos the puntos to set
	 */
	public void setPuntos(long puntos) {
		this.puntos = puntos;
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

	/**
	 * @return the id_sucursal
	 */
	public long getId_sucursal() {
		return id_sucursal;
	}

	/**
	 * @param id_sucursal the id_sucursal to set
	 */
	public void setId_sucursal(long id_sucursal) {
		this.id_sucursal = id_sucursal;
	}
	
	/**
	 * Constructor con parámetros
	 * @param todos los atributos
	 */
	
}
