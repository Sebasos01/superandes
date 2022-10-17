package uniandes.isis2304.superandes.negocio;

public interface VOVentaProducto {
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * @return El num VentaProducto de VentaProducto
	 */
	public long getId_compra();
	/**
	 * @return La idUsuario de VentaProducto
	 */
	public long getId_usuario();
	/**
	 * @return La idProducto de VentaProducto
	 */
	public String getId_producto();
	/**
	 * @return La idSucursal de VentaProducto
	 */
	public long getId_sucursal();
	/**
	 * @return La cantidadProducto de VentaProducto
	 */
	public long getCantidad();
	/**
	 * @return Monto de VentaProducto
	 */
	public long getMonto();
	/**
	 * @return Puntos de VentaProducto
	 */
	public long getPuntos();
	/**
	 * @return La fecha de VentaProducto
	 */
	public String getFecha();
	/**
	 * @return idPromocion de VentaProducto
	 */
	public long getId_promocion();


	/**
	 * @return Una cadena de caracteres con la información de VentaProducto
	 */
	@Override
	public String toString(); 

	/**
	 * Define la igualdad dos VentaProductoes
	 * @param su - La VentaProducto a comparar
	 * @return true si tienen el mismo identificador y el mismo nombre
	 */
	@Override
	public boolean equals (Object su); 
}
