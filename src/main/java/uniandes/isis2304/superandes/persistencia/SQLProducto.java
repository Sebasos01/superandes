package uniandes.isis2304.superandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

class SQLProducto {
		/* ****************************************************************
		 * 			Constantes
		 *****************************************************************/
		/**
		 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
		 * Se renombra acá para facilitar la escritura de las sentencias
		 */
		private final static String SQL = PersistenciaSuperandes.SQL;

		/* ****************************************************************
		 * 			Atributos
		 *****************************************************************/
		/**
		 * El manejador de persistencia general de la aplicación
		 */
		private PersistenciaSuperandes ps;

		/* ****************************************************************
		 * 			Métodos
		 *****************************************************************/
		/**
		 * Constructor
		 * @param pp - El Manejador de persistencia de la aplicación
		 */
		public SQLProducto(PersistenciaSuperandes ps)
		{
			this.ps = ps;
		}
		
		/**
		 * Crea y ejecuta la sentencia SQL para encontrar elas 20 primeras promociones que se acabaron en el menor tiempo
		 * @param pm - El manejador de persistencia
		 */
		
		public List<Object> darProductosPreciosUnaSucursal(PersistenceManager pm, long precioInicial, long precioFinal,long idSucursal)
		{
			
			String sql = "SELECT CODIGO_BARRAS,NOMBRE,MARCA,UNIDAD_MEDIDA,PRESENTACION,CANTIDAD_PRESENTACION,VOLUMEN_EMPAQUE,PESO_EMPAQUE,TIPO,CATEGORIA,FECHA_VENCIMIENTO,PRECIO_VENTA ";
			sql +="FROM ("+ps.darTablaProductoSucursal()+	" INNER JOIN "+ ps.darTablaProducto();
			sql += " ON("+ps.darTablaProducto()+".CODIGO_BARRAS ="+ ps.darTablaProductoSucursal()+".ID_PRODUCTO)) ";
			sql +="WHERE (("+ ps.darTablaProductoSucursal()+".PRECIO_VENTA BETWEEN ? AND ?) AND PRODUCTO_SUCURSAL.ID_SUCURSAL = ?)";
			 Query q = pm.newQuery(SQL, sql);
			 q.setParameters(precioInicial,precioFinal,idSucursal);
			 /*
			 List<Object> list = q.executeList();
			 System.out.println(list);*/
			 return q.executeList();
		}
		public List<Object> darProductosPreciosTodasSucursales(PersistenceManager pm, long precioInicial, long precioFinal)
		{
			
			String sql = "SELECT CODIGO_BARRAS,NOMBRE,MARCA,UNIDAD_MEDIDA,PRESENTACION,CANTIDAD_PRESENTACION,VOLUMEN_EMPAQUE,PESO_EMPAQUE,TIPO,CATEGORIA,FECHA_VENCIMIENTO,PRECIO_VENTA ";
			sql +="FROM ("+ps.darTablaProductoSucursal()+	" INNER JOIN "+ ps.darTablaProducto();
			sql += " ON("+ps.darTablaProducto()+".CODIGO_BARRAS ="+ ps.darTablaProductoSucursal()+".ID_PRODUCTO)) ";
			sql +="WHERE (("+ ps.darTablaProductoSucursal()+".PRECIO_VENTA BETWEEN ? AND ?))";
			 Query q = pm.newQuery(SQL, sql);
			 q.setParameters(precioInicial,precioFinal);
			 /*
			 List<Object> list = q.executeList();
			 System.out.println(list);*/
			 return q.executeList();
		}
		public List<Object> darProductosFechaVencimientoUnaSucursal(PersistenceManager pm,String fechaVencimiento, long idSucursal)
		{
			
			String sql = "SELECT NOMBRE,MARCA,UNIDAD_MEDIDA,PRESENTACION,CANTIDAD_PRESENTACION,VOLUMEN_EMPAQUE,PESO_EMPAQUE,TIPO,CATEGORIA,FECHA_VENCIMIENTO ";
			sql +="FROM ("+ps.darTablaProductoSucursal()+	" INNER JOIN "+ ps.darTablaProducto();
			sql += " ON("+ps.darTablaProducto()+".CODIGO_BARRAS ="+ ps.darTablaProductoSucursal()+".ID_PRODUCTO)) ";
			sql +="WHERE (("+ ps.darTablaProducto()+".FECHA_VENCIMIENTO > to_date(?, 'dd/mm/yyyy')) AND " + ps.darTablaProductoSucursal()+".ID_SUCURSAL = ?)";
			 Query q = pm.newQuery(SQL, sql);
			 q.setParameters(fechaVencimiento,idSucursal);
			 /*
			 List<Object> list = q.executeList();
			 System.out.println(list);*/
			 return q.executeList();
		}
		public List<Object> darProductosFechaVencimientoTodasSucursales(PersistenceManager pm, String fechaVencimiento)
		{
			
			String sql = "SELECT NOMBRE,MARCA,UNIDAD_MEDIDA,PRESENTACION,CANTIDAD_PRESENTACION,VOLUMEN_EMPAQUE,PESO_EMPAQUE,TIPO,CATEGORIA,FECHA_VENCIMIENTO ";
			sql +="FROM ("+ps.darTablaProductoSucursal()+	" INNER JOIN "+ ps.darTablaProducto();
			sql += " ON("+ps.darTablaProducto()+".CODIGO_BARRAS ="+ ps.darTablaProductoSucursal()+".ID_PRODUCTO)) ";
			sql +="WHERE (("+ ps.darTablaProducto()+".FECHA_VENCIMIENTO > to_date(?, 'dd/mm/yyyy')))";
			 Query q = pm.newQuery(SQL, sql);
			 q.setParameters(fechaVencimiento);
			 /*
			 List<Object> list = q.executeList();
			 System.out.println(list);*/
			 return q.executeList();
		}
		public List<Object> darProductosPesoUnaSucursal(PersistenceManager pm, long peso,long idSucursal)
		{
			
			String productoMl = "(SELECT * FROM "+ps.darTablaProducto()+" WHERE (UNIDAD_MEDIDA = 'gr' AND CANTIDAD_PRESENTACION > ?))";
			
			String sql = "SELECT NOMBRE,MARCA,UNIDAD_MEDIDA,PRESENTACION,CANTIDAD_PRESENTACION,VOLUMEN_EMPAQUE,PESO_EMPAQUE,TIPO,CATEGORIA,FECHA_VENCIMIENTO ";
			sql +="FROM ("+ps.darTablaProductoSucursal()+	" INNER JOIN "+ productoMl+"productoGramos ";
			sql += "ON(productoGramos.CODIGO_BARRAS = "+ps.darTablaProductoSucursal()+".ID_PRODUCTO)) ";
			sql +="WHERE ("+ ps.darTablaProductoSucursal()+".ID_SUCURSAL = ?)";

			Query q = pm.newQuery(SQL, sql);
			 q.setParameters(peso, idSucursal);
			 /*
			 List<Object> list = q.executeList();
			 System.out.println(list);*/
			 return q.executeList();
		}
		public List<Object> darProductosPesoTodasSucursales(PersistenceManager pm, long peso)
		{
			
			String productoMl = "(SELECT * FROM "+ps.darTablaProducto()+" WHERE (UNIDAD_MEDIDA = 'gr' AND CANTIDAD_PRESENTACION > ?))";
			
			String sql = "SELECT NOMBRE,MARCA,UNIDAD_MEDIDA,PRESENTACION,CANTIDAD_PRESENTACION,VOLUMEN_EMPAQUE,PESO_EMPAQUE,TIPO,CATEGORIA,FECHA_VENCIMIENTO ";
			sql +="FROM ("+ps.darTablaProductoSucursal()+	" INNER JOIN "+ productoMl+"productoGramos ";
			sql += "ON(productoGramos.CODIGO_BARRAS = "+ps.darTablaProductoSucursal()+".ID_PRODUCTO)) ";

			Query q = pm.newQuery(SQL, sql);
			 q.setParameters(peso);
			 /*
			 List<Object> list = q.executeList();
			 System.out.println(list);*/
			 return q.executeList();
		}
		public List<Object> darProductosTipoUnaSucursal(PersistenceManager pm, String tipo,long idSucursal)
		{
			
			String sql = "SELECT NOMBRE,MARCA,UNIDAD_MEDIDA,PRESENTACION,CANTIDAD_PRESENTACION,VOLUMEN_EMPAQUE,PESO_EMPAQUE,TIPO,CATEGORIA,FECHA_VENCIMIENTO ";
			sql +="FROM ("+ps.darTablaProductoSucursal()+	" INNER JOIN "+ ps.darTablaProducto();
			sql += " ON("+ps.darTablaProducto()+".CODIGO_BARRAS ="+ ps.darTablaProductoSucursal()+".ID_PRODUCTO)) ";
			sql +="WHERE (("+ ps.darTablaProducto()+".TIPO = ?)  AND " + ps.darTablaProductoSucursal()+".ID_SUCURSAL = ?)";
			 Query q = pm.newQuery(SQL, sql);
			 q.setParameters(tipo,idSucursal);
			 /*
			 List<Object> list = q.executeList();
			 System.out.println(list);*/
			 return q.executeList();
		}
		public List<Object> darProductosTipoTodasSucursales(PersistenceManager pm, String tipo)
		{
			
			String sql = "SELECT NOMBRE,MARCA,UNIDAD_MEDIDA,PRESENTACION,CANTIDAD_PRESENTACION,VOLUMEN_EMPAQUE,PESO_EMPAQUE,TIPO,CATEGORIA,FECHA_VENCIMIENTO ";
			sql +="FROM ("+ps.darTablaProductoSucursal()+	" INNER JOIN "+ ps.darTablaProducto();
			sql += " ON("+ps.darTablaProducto()+".CODIGO_BARRAS ="+ ps.darTablaProductoSucursal()+".ID_PRODUCTO)) ";
			sql +="WHERE (("+ ps.darTablaProducto()+".TIPO = ?) )";
			 Query q = pm.newQuery(SQL, sql);
			 q.setParameters(tipo);
			 /*
			 List<Object> list = q.executeList();
			 System.out.println(list);*/
			 return q.executeList();
		}
		
		public List<Object> darProductosXUnidadesUnaSucursal(PersistenceManager pm, String fechaInicial, String fechaFinal,long idSucursal, long unidades)
		{
			
			String sql ="SELECT NOMBRE,MARCA,UNIDAD_MEDIDA,PRESENTACION,CANTIDAD_PRESENTACION,VOLUMEN_EMPAQUE,PESO_EMPAQUE,TIPO,CATEGORIA,FECHA_VENCIMIENTO, UNIDADES ";
			sql +="FROM ((SELECT ID_PRODUCTO, COUNT(*) AS UNIDADES	FROM ("+ps.darTablaVentaProducto()+") ";
			sql +="	WHERE ((FECHA BETWEEN to_date(?, 'dd/mm/yyyy') AND  to_date(?, 'dd/mm/yyyy')) AND ID_SUCURSAL = ? )";
			sql+= "GROUP BY ID_PRODUCTO	HAVING COUNT(*)>= ?	)productosCumplen ";
			sql +="INNER JOIN "+ps.darTablaProducto()+" ON(" +ps.darTablaProducto()+".CODIGO_BARRAS = productosCumplen.ID_PRODUCTO))";
					
			 Query q = pm.newQuery(SQL, sql);
			 q.setParameters(fechaInicial,fechaFinal,idSucursal,unidades);
			 /*
			 List<Object> list = q.executeList();
			 System.out.println(list);*/
			 return q.executeList();
		}
		
		public List<Object> darProductosXUnidadesTodasSucursales(PersistenceManager pm, String fechaInicial, String fechaFinal, long unidades)
		{
			
			String sql ="SELECT NOMBRE,MARCA,UNIDAD_MEDIDA,PRESENTACION,CANTIDAD_PRESENTACION,VOLUMEN_EMPAQUE,PESO_EMPAQUE,TIPO,CATEGORIA,FECHA_VENCIMIENTO, UNIDADES ";
			sql +="FROM ((SELECT ID_PRODUCTO, COUNT(*) AS UNIDADES	FROM ("+ps.darTablaVentaProducto()+") ";
			sql +="	WHERE ((FECHA BETWEEN to_date(?, 'dd/mm/yyyy') AND  to_date(?, 'dd/mm/yyyy')) )";
			sql+= "GROUP BY ID_PRODUCTO	HAVING COUNT(*)>= ?	)productosCumplen ";
			sql +="INNER JOIN "+ps.darTablaProducto()+" ON(" +ps.darTablaProducto()+".CODIGO_BARRAS = productosCumplen.ID_PRODUCTO))";
					
			 Query q = pm.newQuery(SQL, sql);
			 q.setParameters(fechaInicial,fechaFinal,unidades);
			 /*
			 List<Object> list = q.executeList();
			 System.out.println(list);*/
			 return q.executeList();
		}

}
