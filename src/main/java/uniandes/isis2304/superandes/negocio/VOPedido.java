package uniandes.isis2304.superandes.negocio;

import java.sql.Timestamp;

public interface VOPedido {
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * @return El num pedido de Pedido
	 */
	public long getNum_pedido();

	/**
	 * @return El id producto de Pedido
	 */
	public String getId_producto();
	
	/**
	 * @return El idProveedor de Pedido
	 */
	public String getId_proveedor();
	
	/**
	 * @return La idSucursal de Pedido
	 */
	public long getId_sucursal();
	
	/**
	 * @return La cantidadProducto de Pedido
	 */
	public long getCantidad_producto();
	/**
	 * @return La cantidadProducto de Pedido
	 */
	public long getPrecio_total();
	/**
	 * @return La cantidadProducto de Pedido
	 */
	public Timestamp getInicio();
	/**
	 * @return La cantidadProducto de Pedido
	 */
	public long getDias_entrega();
	/**
	 * @return La cantidadProducto de Pedido
	 */
	public String getEstado();
	/**
	 * @return La cantidadProducto de Pedido
	 */
	public Timestamp getLlegada();

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
