package uniandes.isis2304.superandes.negocio;

import java.sql.Timestamp;

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
	public long getId_sucursal();

	/**
	 * @return El inicio de Promocion
	 */
	public Timestamp getInicio();
	
	/**
	 * @return La duracion de Promocion
	 */
	public long getDuracion();
	
	/**
	 * @return El fin de Promocion
	 */
	public Timestamp getFin();
	
	/**
	 * @return La ventasMaximas de Promocion
	 */
	public long getVentas_maximas();
	/**
	 * @return La ventasMinimas de Promocion
	 */
	public long getVentas_actuales();
	/**
	 * @return Precio paquete de Promocion
	 */
	public long getPrecio_paquete();
	
	/**
	 * @return IdProducto de Promocion
	 */
	public String getId_producto();
	
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
