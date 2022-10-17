package uniandes.isis2304.superandes.negocio;

import java.sql.Timestamp;

public class Producto implements VOProducto{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/

	private String codigo_barras;
	private String nombre;
	private String marca;
	private String unidad_medida;
	private String presentacion;
	private long cantidad_presentacion;
	private long volumen_empaque;
	private long peso_empaque;
	private String tipo;
	private String categoria;
	private Timestamp fecha_vencimiento;
	
	public Producto(String codigo_barras, String nombre, String marca, String unidad_medida, String presentacion,
			long cantidad_presentacion, long volumen_empaque, long peso_empaque, String tipo, String categoria,
			Timestamp fecha_vencimiento) {
		super();
		this.codigo_barras = codigo_barras;
		this.nombre = nombre;
		this.marca = marca;
		this.unidad_medida = unidad_medida;
		this.presentacion = presentacion;
		this.cantidad_presentacion = cantidad_presentacion;
		this.volumen_empaque = volumen_empaque;
		this.peso_empaque = peso_empaque;
		this.tipo = tipo;
		this.categoria = categoria;
		this.fecha_vencimiento = fecha_vencimiento;
	}

	
	
	/**
	 * @return the codigo_barras
	 */
	public String getCodigo_barras() {
		return codigo_barras;
	}



	/**
	 * @param codigo_barras the codigo_barras to set
	 */
	public void setCodigo_barras(String codigo_barras) {
		this.codigo_barras = codigo_barras;
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
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}



	/**
	 * @param marca the marca to set
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}



	/**
	 * @return the unidad_medida
	 */
	public String getUnidad_medida() {
		return unidad_medida;
	}



	/**
	 * @param unidad_medida the unidad_medida to set
	 */
	public void setUnidad_medida(String unidad_medida) {
		this.unidad_medida = unidad_medida;
	}



	/**
	 * @return the presentacion
	 */
	public String getPresentacion() {
		return presentacion;
	}



	/**
	 * @param presentacion the presentacion to set
	 */
	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}



	/**
	 * @return the cantidad_presentacion
	 */
	public long getCantidad_presentacion() {
		return cantidad_presentacion;
	}



	/**
	 * @param cantidad_presentacion the cantidad_presentacion to set
	 */
	public void setCantidad_presentacion(long cantidad_presentacion) {
		this.cantidad_presentacion = cantidad_presentacion;
	}



	/**
	 * @return the volumen_empaque
	 */
	public long getVolumen_empaque() {
		return volumen_empaque;
	}



	/**
	 * @param volumen_empaque the volumen_empaque to set
	 */
	public void setVolumen_empaque(long volumen_empaque) {
		this.volumen_empaque = volumen_empaque;
	}



	/**
	 * @return the peso_empaque
	 */
	public long getPeso_empaque() {
		return peso_empaque;
	}



	/**
	 * @param peso_empaque the peso_empaque to set
	 */
	public void setPeso_empaque(long peso_empaque) {
		this.peso_empaque = peso_empaque;
	}



	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}



	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}



	/**
	 * @return the categoria
	 */
	public String getCategoria() {
		return categoria;
	}



	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}



	/**
	 * @return the fecha_vencimiento
	 */
	public Timestamp getFecha_vencimiento() {
		return fecha_vencimiento;
	}



	/**
	 * @param fecha_vencimiento the fecha_vencimiento to set
	 */
	public void setFecha_vencimiento(Timestamp fecha_vencimiento) {
		this.fecha_vencimiento = fecha_vencimiento;
	}



	@Override
	public String toString() {
		return "Producto [codigo_barras=" + codigo_barras + ", nombre=" + nombre + ", marca=" + marca
				+ ", unidad_medida=" + unidad_medida + ", presentacion=" + presentacion + ", cantidad_presentacion="
				+ cantidad_presentacion + ", volumen_empaque=" + volumen_empaque + ", peso_empaque=" + peso_empaque
				+ ", tipo=" + tipo + ", categoria=" + categoria + ", fecha_vencimiento=" + fecha_vencimiento + "]";
	}
	
	
}
