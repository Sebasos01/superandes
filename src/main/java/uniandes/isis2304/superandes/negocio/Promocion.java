package uniandes.isis2304.superandes.negocio;

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
	private long idSucursal;

	/**
	 * El inicio de Promocion
	 */
	private String inicio;
	/**
	 * Duracion de Promocion
	 */
	private long duracion;
	/**
	 *  fin  de Promocion
	 */
	private String fin;
	/**
	 * ventasMaximas de Promocion
	 */
	private long ventasMaximas;
	/**
	 * ventasActuales de Promocion
	 */
	private long ventasActuales;
	/**
	 * precioPaquete de Promocion
	 */
	private long precioPaquete;
	/**
	 * El idProducto de Promocion
	 */
	private String idProducto;
	/**
	 * La descripcion de Promocion
	 */
	private String descripcion;
	
	
	
	public Promocion(long id, long idSucursal, String inicio, long duracion, String fin, long ventasMaximas,
			long ventasActuales, long precioPaquete, String idProducto, String descripcion) {

		this.id = id;
		this.idSucursal = idSucursal;
		this.inicio = inicio;
		this.duracion = duracion;
		this.fin = fin;
		this.ventasMaximas = ventasMaximas;
		this.ventasActuales = ventasActuales;
		this.precioPaquete = precioPaquete;
		this.idProducto = idProducto;
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
	 * @return the idSucursal
	 */
	public long getIdSucursal() {
		return idSucursal;
	}
	/**
	 * @param idSucursal the idSucursal to set
	 */
	public void setIdSucursal(long idSucursal) {
		this.idSucursal = idSucursal;
	}
	/**
	 * @return the inicio
	 */
	public String getInicio() {
		return inicio;
	}
	/**
	 * @param inicio the inicio to set
	 */
	public void setInicio(String inicio) {
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
	public String getFin() {
		return fin;
	}
	/**
	 * @param fin the fin to set
	 */
	public void setFin(String fin) {
		this.fin = fin;
	}
	/**
	 * @return the ventasMaximas
	 */
	public long getVentasMaximas() {
		return ventasMaximas;
	}
	/**
	 * @param ventasMaximas the ventasMaximas to set
	 */
	public void setVentasMaximas(long ventasMaximas) {
		this.ventasMaximas = ventasMaximas;
	}
	/**
	 * @return the ventasActuales
	 */
	public long getVentasActuales() {
		return ventasActuales;
	}
	/**
	 * @param ventasActuales the ventasActuales to set
	 */
	public void setVentasActuales(long ventasActuales) {
		this.ventasActuales = ventasActuales;
	}
	/**
	 * @return the precioPaquete
	 */
	public long getPrecioPaquete() {
		return precioPaquete;
	}
	/**
	 * @param precioPaquete the precioPaquete to set
	 */
	public void setPrecioPaquete(long precioPaquete) {
		this.precioPaquete = precioPaquete;
	}
	/**
	 * @return the idProducto
	 */
	public String getIdProducto() {
		return idProducto;
	}
	/**
	 * @param idProducto the idProducto to set
	 */
	public void setIdProducto(String idProducto) {
		this.idProducto = idProducto;
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
		return "Promocion [id=" + id + ", idSucursal=" + idSucursal + ", inicio=" + inicio + ", duracion=" + duracion
				+ ", fin=" + fin + ", ventasMaximas=" + ventasMaximas + ", ventasActuales=" + ventasActuales
				+ ", precioPaquete=" + precioPaquete + ", idProducto=" + idProducto + ", descripcion=" + descripcion
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
