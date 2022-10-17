package uniandes.isis2304.superandes.negocio;

import java.sql.Timestamp;

public class Pedido implements VOPedido {
	public long num_pedido; 
	public String id_producto;
	public String id_proveedor;
	public long id_sucursal; 
	public long cantidad_producto; 
	public long precio_total; 
	public Timestamp inicio;
	public long dias_entrega; 
	public String estado;
	public Timestamp llegada;
	

	public Pedido(long num_pedido, String id_producto, String id_proveedor, long id_sucursal, long cantidad_producto,
			long precio_total, Timestamp inicio, long dias_entrega, String estado, Timestamp llegada) {
		super();
		this.num_pedido = num_pedido;
		this.id_producto = id_producto;
		this.id_proveedor = id_proveedor;
		this.id_sucursal = id_sucursal;
		this.cantidad_producto = cantidad_producto;
		this.precio_total = precio_total;
		this.inicio = inicio;
		this.dias_entrega = dias_entrega;
		this.estado = estado;
		this.llegada = llegada;
	}


	/**
	 * @return the num_pedido
	 */
	public long getNum_pedido() {
		return num_pedido;
	}


	/**
	 * @param num_pedido the num_pedido to set
	 */
	public void setNum_pedido(long num_pedido) {
		this.num_pedido = num_pedido;
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
	 * @return the id_proveedor
	 */
	public String getId_proveedor() {
		return id_proveedor;
	}


	/**
	 * @param id_proveedor the id_proveedor to set
	 */
	public void setId_proveedor(String id_proveedor) {
		this.id_proveedor = id_proveedor;
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
	 * @return the cantidad_producto
	 */
	public long getCantidad_producto() {
		return cantidad_producto;
	}


	/**
	 * @param cantidad_producto the cantidad_producto to set
	 */
	public void setCantidad_producto(long cantidad_producto) {
		this.cantidad_producto = cantidad_producto;
	}


	/**
	 * @return the precio_total
	 */
	public long getPrecio_total() {
		return precio_total;
	}


	/**
	 * @param precio_total the precio_total to set
	 */
	public void setPrecio_total(long precio_total) {
		this.precio_total = precio_total;
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
	 * @return the dias_entrega
	 */
	public long getDias_entrega() {
		return dias_entrega;
	}


	/**
	 * @param dias_entrega the dias_entrega to set
	 */
	public void setDias_entrega(long dias_entrega) {
		this.dias_entrega = dias_entrega;
	}


	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}


	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}


	/**
	 * @return the llegada
	 */
	public Timestamp getLlegada() {
		return llegada;
	}


	/**
	 * @param llegada the llegada to set
	 */
	public void setLlegada(Timestamp llegada) {
		this.llegada = llegada;
	}


	@Override
	public String toString() {
		return "Pedido [num_pedido=" + num_pedido + ", id_producto=" + id_producto + ", id_proveedor=" + id_proveedor
				+ ", id_sucursal=" + id_sucursal + ", cantidad_producto=" + cantidad_producto + ", precio_total="
				+ precio_total + ", inicio=" + inicio + ", dias_entrega=" + dias_entrega + ", estado=" + estado
				+ ", llegada=" + llegada + "]";
	}


	public boolean equals(Object sucursal) 
	{
		Pedido su = (Pedido) sucursal;
		return num_pedido == su.num_pedido && id_producto == su.id_producto && id_proveedor == su.id_proveedor&& id_sucursal == su.id_sucursal; 
	}
	
	
	
}
