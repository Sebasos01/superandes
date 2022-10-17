package uniandes.isis2304.superandes.negocio;

public class Pedido {
	public long numPedido; 
	public String idProducto;
	public String idProveedor;
	public long idSucursal; 
	public long cantidadProducto; 
	public long precioTotal; 
	public String inicio;
	public long diasEntrega; 
	public String estado;
	public String llegada;
	
	
	
	public Pedido(long numPedido, String idProducto, String idProveedor, long idSucursal, long cantidadProducto,
			long precioTotal, String inicio, long diasEntrega, String estado, String llegada) {
		super();
		this.numPedido = numPedido;
		this.idProducto = idProducto;
		this.idProveedor = idProveedor;
		this.idSucursal = idSucursal;
		this.cantidadProducto = cantidadProducto;
		this.precioTotal = precioTotal;
		this.inicio = inicio;
		this.diasEntrega = diasEntrega;
		this.estado = estado;
		this.llegada = llegada;
	}
	/**
	 * @return the numPedido
	 */
	public long getNumPedido() {
		return numPedido;
	}
	/**
	 * @param numPedido the numPedido to set
	 */
	public void setNumPedido(long numPedido) {
		this.numPedido = numPedido;
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
	 * @return the idProveedor
	 */
	public String getIdProveedor() {
		return idProveedor;
	}
	/**
	 * @param idProveedor the idProveedor to set
	 */
	public void setIdProveedor(String idProveedor) {
		this.idProveedor = idProveedor;
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
	 * @return the cantidadProducto
	 */
	public long getCantidadProducto() {
		return cantidadProducto;
	}
	/**
	 * @param cantidadProducto the cantidadProducto to set
	 */
	public void setCantidadProducto(long cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}
	/**
	 * @return the precioTotal
	 */
	public long getPrecioTotal() {
		return precioTotal;
	}
	/**
	 * @param precioTotal the precioTotal to set
	 */
	public void setPrecioTotal(long precioTotal) {
		this.precioTotal = precioTotal;
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
	 * @return the diasEntrega
	 */
	public long getDiasEntrega() {
		return diasEntrega;
	}
	/**
	 * @param diasEntrega the diasEntrega to set
	 */
	public void setDiasEntrega(long diasEntrega) {
		this.diasEntrega = diasEntrega;
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
	public String getLlegada() {
		return llegada;
	}
	/**
	 * @param llegada the llegada to set
	 */
	public void setLlegada(String llegada) {
		this.llegada = llegada;
	}
	@Override
	public String toString() {
		return "Pedido [numPedido=" + numPedido + ", idProducto=" + idProducto + ", idProveedor=" + idProveedor
				+ ", idSucursal=" + idSucursal + ", cantidadProducto=" + cantidadProducto + ", precioTotal="
				+ precioTotal + ", inicio=" + inicio + ", diasEntrega=" + diasEntrega + ", estado=" + estado
				+ ", llegada=" + llegada + "]";
	}
	public boolean equals(Object sucursal) 
	{
		Pedido su = (Pedido) sucursal;
		return numPedido == su.numPedido && idProducto == su.idProducto && idProveedor == su.idProveedor&& idSucursal == su.idSucursal; 
	}
	
	
	
}
