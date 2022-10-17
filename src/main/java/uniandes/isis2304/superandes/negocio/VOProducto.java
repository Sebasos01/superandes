package uniandes.isis2304.superandes.negocio;

import java.sql.Timestamp;

public interface VOProducto {
	public String getCodigo_barras();
	public String getNombre();
	public String getMarca();
	public String getUnidad_medida();
	public String getPresentacion();
	public long getCantidad_presentacion();
	public long getVolumen_empaque();
	public long getPeso_empaque();
	public String getTipo();
	public String getCategoria();
	public Timestamp getFecha_vencimiento();

}
