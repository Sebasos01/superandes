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
	
	/**
	 * Constructor con parámetros
	 * @param todos los atributos
	 */
	public Usuario(long codigo_usuario, String documento, String tipo_documento, String nombre, String email,
			String contrasena, long id_tipo, long id_sucursal) {
		this.codigo_usuario = codigo_usuario;
		this.documento = documento;
		this.tipo_documento = tipo_documento;
		this.nombre = nombre;
		this.email = email;
		this.contrasena = contrasena;
		this.id_tipo = id_tipo;
		this.id_sucursal = id_sucursal;
	}
	
	/**
	 * @return el código del usuario
	 */
	public long getCodigo_usuario() {
		return codigo_usuario;
	}

	/**
	 * @param el nuevo código del usuario
	 */
	public void setCodigo_usuario(long codigo_usuario) {
		this.codigo_usuario = codigo_usuario;
	}

	/**
	 * @return el documento del usuario
	 */
	public String getDocumento() {
		return documento;
	}

	/**
	 * @param el nuevo documento del usuario
	 */
	public void setDocumento(String documento) {
		this.documento = documento;
	}

	/**
	 * @return el tipo de documento del usuario
	 */
	public String getTipo_documento() {
		return tipo_documento;
	}

	/**
	 * @param el nuevo tipo de documento 
	 */
	public void setTipo_documento(String tipo_documento) {
		this.tipo_documento = tipo_documento;
	}

	/**
	 * @return el nombre del usuario
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param el nuevo nombre del usuario
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return el email del usuario 
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param el nuevo email del usuario
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return la contraseña del usuario
	 */
	public String getContrasena() {
		return contrasena;
	}

	/**
	 * @param la nueva contraseña del usuario
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	/**
	 * @return el id del tipo de usuario
	 */
	public long getId_tipo() {
		return id_tipo;
	}

	/**
	 * @param el nuevo id del tipo de usuario
	 */
	public void setId_tipo(long id_tipo) {
		this.id_tipo = id_tipo;
	}

	/**
	 * @return el id de la sucursal
	 */
	public long getId_sucursal() {
		return id_sucursal;
	}

	/**
	 * @param el nuevo id de la sucursal
	 */
	public void setId_sucursal(long id_sucursal) {
		this.id_sucursal = id_sucursal;
	}

	/**
	 * @return Una cadena de caracteres con la información del usuario
	 */
	@Override
	public String toString() 
	{
		return String.format("[id=%d, nombre=%s, documento=%s, tipo_documento=%s, email=%s, id_tipo=%d, id_sucursal=%d]", 
				codigo_usuario, nombre, documento, tipo_documento, email, id_tipo, id_sucursal);
	}
}
