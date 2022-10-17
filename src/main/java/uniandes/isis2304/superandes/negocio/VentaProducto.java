package uniandes.isis2304.superandes.negocio;

import java.math.BigDecimal;

public class VentaProducto implements VOVentaProducto {
	
	long id_compra ;
	long id_usuario;
	String id_producto ;
	long id_sucursal ;
	long cantidad ;
	long monto;
	long puntos ;
	String fecha ;
	long id_promocion;
	
	public VentaProducto(long id_compra, long id_usuario, String id_producto, long id_sucursal, long cantidad,
			long monto, long puntos, String fecha, long id_promocion) {
		super();
		this.id_compra = id_compra;
		this.id_usuario = id_usuario;
		this.id_producto = id_producto;
		this.id_sucursal = id_sucursal;
		this.cantidad = cantidad;
		this.monto = monto;
		this.puntos = puntos;
		this.fecha = fecha;
		this.id_promocion = id_promocion;
	}

	

	
	/**
	 * @return the id_compra
	 */
	public long getId_compra() {
		return id_compra;
	}


	/**
	 * @param id_compra the id_compra to set
	 */
	public void setId_compra(long id_compra) {
		this.id_compra = id_compra;
	}


	/**
	 * @return the id_usuario
	 */
	public long getId_usuario() {
		return id_usuario;
	}


	/**
	 * @param id_usuario the id_usuario to set
	 */
	public void setId_usuario(long id_usuario) {
		this.id_usuario = id_usuario;
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
	 * @return the id_promocion
	 */
	public long getId_promocion() {
		return id_promocion;
	}





	/**
	 * @param id_promocion the id_promocion to set
	 */
	public void setId_promocion(long id_promocion) {
		this.id_promocion = id_promocion;
	}





	




	public boolean equals(Object venta) 
	{
		VentaProducto v = (VentaProducto) venta;
		return id_compra == v.id_compra && id_usuario == v.id_usuario && id_producto == v.id_producto && id_sucursal == v.id_sucursal; 
	}

}
