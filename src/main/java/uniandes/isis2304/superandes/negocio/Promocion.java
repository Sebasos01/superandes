package uniandes.isis2304.superandes.negocio;

import java.sql.Timestamp;

public class Promocion implements VOPromocion {
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador de Promocion
	 */
	private long id;
	/**
	 * El identificador de Promocion
	 */
	private long id_sucursal;

	/**
	 * El inicio de Promocion
	 */
	private Timestamp inicio;
	/**
	 * Duracion de Promocion
	 */
	private long duracion;
	/**
	 *  fin  de Promocion
	 */
	private Timestamp fin;
	/**
	 * ventasMaximas de Promocion
	 */
	private long ventas_maximas;
	/**
	 * ventasActuales de Promocion
	 */
	private long ventas_actuales;
	/**
	 * precioPaquete de Promocion
	 */
	private long precio_paquete;
	/**
	 * El idProducto de Promocion
	 */
	private String id_producto;
	/**
	 * La descripcion de Promocion
	 */
	private String descripcion;
	
	
	

	public Promocion(long id, long id_sucursal, Timestamp inicio, long duracion, Timestamp fin, long ventas_maximas,
			long ventas_actuales, long precio_paquete, String id_producto, String descripcion) {
		super();
		this.id = id;
		this.id_sucursal = id_sucursal;
		this.inicio = inicio;
		this.duracion = duracion;
		this.fin = fin;
		this.ventas_maximas = ventas_maximas;
		this.ventas_actuales = ventas_actuales;
		this.precio_paquete = precio_paquete;
		this.id_producto = id_producto;
		this.descripcion = descripcion;
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
	 * @return the inicio
	 */
	public Timestamp getInicio() {
		return inicio;
	}




	/**
	 * @param inicio the inicio to set
	 */
	public void setInicio(Timestamp inicio) {
		this.inicio = inicio;
	}




	/**
	 * @return the duracion
	 */
	public long getDuracion() {
		return duracion;
	}




	/**
	 * @param duracion the duracion to set
	 */
	public void setDuracion(long duracion) {
		this.duracion = duracion;
	}




	/**
	 * @return the fin
	 */
	public Timestamp getFin() {
		return fin;
	}




	/**
	 * @param fin the fin to set
	 */
	public void setFin(Timestamp fin) {
		this.fin = fin;
	}




	/**
	 * @return the ventas_maximas
	 */
	public long getVentas_maximas() {
		return ventas_maximas;
	}




	/**
	 * @param ventas_maximas the ventas_maximas to set
	 */
	public void setVentas_maximas(long ventas_maximas) {
		this.ventas_maximas = ventas_maximas;
	}




	/**
	 * @return the ventas_actuales
	 */
	public long getVentas_actuales() {
		return ventas_actuales;
	}




	/**
	 * @param ventas_actuales the ventas_actuales to set
	 */
	public void setVentas_actuales(long ventas_actuales) {
		this.ventas_actuales = ventas_actuales;
	}




	/**
	 * @return the precio_paquete
	 */
	public long getPrecio_paquete() {
		return precio_paquete;
	}




	/**
	 * @param precio_paquete the precio_paquete to set
	 */
	public void setPrecio_paquete(long precio_paquete) {
		this.precio_paquete = precio_paquete;
	}




	/**
	 * @return the id_producto
	 */
	public String getId_producto() {
		return id_producto;
	}




	/**
	 * @param id_producto the id_producto to set
	 */
	public void setId_producto(String id_producto) {
		this.id_producto = id_producto;
	}




	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}




	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}








	@Override
	public String toString() {
		return "Promocion [id=" + id + ", id_sucursal=" + id_sucursal + ", inicio=" + inicio + ", duracion=" + duracion
				+ ", fin=" + fin + ", ventas_maximas=" + ventas_maximas + ", ventas_actuales=" + ventas_actuales
				+ ", precio_paquete=" + precio_paquete + ", id_producto=" + id_producto + ", descripcion=" + descripcion
				+ "]";
	}




	/**
	 * @param promocion
	 * @return True si tienen el mismo id
	 */
	public boolean equals(Object promocion) 
	{
		Promocion pr = (Promocion) promocion;
		return id == pr.id ;
	}
	

}
