package uniandes.isis2304.superandes.negocio;

public interface VOPromocion {
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * @return El id de Promocion
	 */
	public long getId();
	
	/**
	 * @return El id sucursal de Promocion
	 */
	public long getIdSucursal();

	/**
	 * @return El inicio de Promocion
	 */
	public String getInicio();
	
	/**
	 * @return La duracion de Promocion
	 */
	public long getDuracion();
	
	/**
	 * @return El fin de Promocion
	 */
	public String getFin();
	
	/**
	 * @return La ventasMaximas de Promocion
	 */
	public long getVentasMaximas();
	/**
	 * @return La ventasMinimas de Promocion
	 */
	public long getVentasActuales();
	/**
	 * @return Precio paquete de Promocion
	 */
	public long getPrecioPaquete();
	
	/**
	 * @return IdProducto de Promocion
	 */
	public String getIdProducto();
	
	/**
	 * @return descripcion de Promocion
	 */
	public String getDescripcion();

	/**
	 * @return Una cadena de caracteres con la información de Promocion
	 */
	@Override
	public String toString(); 

	/**
	 * Define la igualdad dos Promociones
	 * @param su - La Promocion a comparar
	 * @return true si tienen el mismo identificador y el mismo nombre
	 */
	@Override
	public boolean equals (Object su); 

}
