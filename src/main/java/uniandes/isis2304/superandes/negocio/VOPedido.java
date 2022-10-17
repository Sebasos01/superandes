package uniandes.isis2304.superandes.negocio;

public interface VOPedido {
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * @return El num pedido de Pedido
	 */
	public long getNumPedido();

	/**
	 * @return El id producto de Pedido
	 */
	public String getIdProducto();
	
	/**
	 * @return El idProveedor de Pedido
	 */
	public String getIdProveedor();
	
	/**
	 * @return La idSucursal de Pedido
	 */
	public long getIdSucursal();
	
	/**
	 * @return La cantidadProducto de Pedido
	 */
	public long getCantidadProducto();
	/**
	 * @return La cantidadProducto de Pedido
	 */
	public long getPrecioTotal();
	/**
	 * @return La cantidadProducto de Pedido
	 */
	public String getInicio();
	/**
	 * @return La cantidadProducto de Pedido
	 */
	public long getDiasEntrega();
	/**
	 * @return La cantidadProducto de Pedido
	 */
	public String getEstado();
	/**
	 * @return La cantidadProducto de Pedido
	 */
	public String getLlegada();

	/**
	 * @return Una cadena de caracteres con la información de Pedido
	 */
	@Override
	public String toString(); 

	/**
	 * Define la igualdad dos Pedidoes
	 * @param su - La Pedido a comparar
	 * @return true si tienen el mismo identificador y el mismo nombre
	 */
	@Override
	public boolean equals (Object su); 

}
