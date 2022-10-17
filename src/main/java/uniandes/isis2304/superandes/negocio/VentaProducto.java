package uniandes.isis2304.superandes.negocio;

import java.math.BigDecimal;

public class VentaProducto {
	
	long idCompra ;
	long idUsuario;
	String idProducto ;
	long idSucursal ;
	long cantidad ;
	long monto;
	long puntos ;
	String fecha ;
	long idPromocion;
	
	
	
	public VentaProducto(long idCompra, long idUsuario, String idProducto, long idSucursal, long cantidad, long monto,
			long puntos, String fecha, long idPromocion) {
		super();
		this.idCompra = idCompra;
		this.idUsuario = idUsuario;
		this.idProducto = idProducto;
		this.idSucursal = idSucursal;
		this.cantidad = cantidad;
		this.monto = monto;
		this.puntos = puntos;
		this.fecha = fecha;
		this.idPromocion = idPromocion;
	}
	/**
	 * @return the idCompra
	 */
	public long getIdCompra() {
		return idCompra;
	}
	/**
	 * @param idCompra the idCompra to set
	 */
	public void setIdCompra(long idCompra) {
		this.idCompra = idCompra;
	}
	/**
	 * @return the idUsuario
	 */
	public long getIdUsuario() {
		return idUsuario;
	}
	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
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
	 * @return the cantidad
	 */
	public long getCantidad() {
		return cantidad;
	}
	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(long cantidad) {
		this.cantidad = cantidad;
	}
	/**
	 * @return the monto
	 */
	public long getMonto() {
		return monto;
	}
	/**
	 * @param monto the monto to set
	 */
	public void setMonto(long monto) {
		this.monto = monto;
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
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return the idPromocion
	 */
	public long getIdPromocion() {
		return idPromocion;
	}
	/**
	 * @param idPromocion the idPromocion to set
	 */
	public void setIdPromocion(long idPromocion) {
		this.idPromocion = idPromocion;
	}
	@Override
	public String toString() {
		return "VentaProducto [idCompra=" + idCompra + ", idUsuario=" + idUsuario + ", idProducto=" + idProducto
				+ ", idSucursal=" + idSucursal + ", cantidad=" + cantidad + ", monto=" + monto + ", puntos=" + puntos
				+ ", fecha=" + fecha + ", idPromocion=" + idPromocion + "]";
	}
	
	public boolean equals(Object venta) 
	{
		VentaProducto v = (VentaProducto) venta;
		return idCompra == v.idCompra && idUsuario == v.idUsuario && idProducto == v.idProducto && idSucursal == v.idSucursal; 
	}

}
