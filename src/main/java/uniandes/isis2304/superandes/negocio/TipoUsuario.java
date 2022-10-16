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
 * Clase para modelar el concepto TipoUsuario del negocio de Superandes
 *
 * @author Sebastián Ospino
 */
public class TipoUsuario implements VOTipoUsuario
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador del tipo de usuario
	 */
	private long id;

	/**
	 * El nombre del tipo de usuario
	 */
	private String nombre;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Único constructor
	 * @param id - El identificador del tipo de usuario
	 * @param nombre - El nombre del tipo de usuario
	 */
	public TipoUsuario(long id, String nombre) 
	{
		this.id = id;
		this.nombre = nombre;
	}

	/**
	 * @return El id del tipo de usuario
	 */
	public long getId() 
	{
		return id;
	}

	/**
	 * @param id - El nuevo id del tipo de usuario
	 */
	public void setId(long id) 
	{
		this.id = id;
	}

	/**
	 * @return El nombre del tipo de usuario
	 */
	public String getNombre() 
	{
		return nombre;
	}

	/**
	 * @param nombre - El nuevo nombre del tipo de usuario
	 */
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}


	/**
	 * @return Una cadena de caracteres con la información del tipo de usuario
	 */
	@Override
	public String toString() 
	{
		return "TipoUsuario [id=" + id + ", nombre=" + nombre + "]";
	}

	/**
	 * @param tipo - El TipoUsuario a comparar
	 * @return True si tienen el mismo nombre
	 */
	public boolean equals(Object tipo) 
	{
		TipoUsuario tu = (TipoUsuario) tipo;
		return id == tu.id && nombre.equalsIgnoreCase (tu.nombre);
	}
}
