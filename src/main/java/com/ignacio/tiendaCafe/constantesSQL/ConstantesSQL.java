package com.ignacio.tiendaCafe.constantesSQL;

public class ConstantesSQL {
	public final static String SQL_OBTENER_DETALLES_CAFE = "select c.id, c.cuerpo, c.descripcion, c.grano, c.nombre_cafe, c.precio, c.region from tabla_cafes as c where  c.id= :id"; 
	public final static String SQL_OBTENER_LISTADO_CAFES = "select c.id, c.precio, c.nombre_cafe from tabla_cafes as c"; 
	public static final String SQL_OBTENER_CAFES_CARRITO = 
			"select tc.id, tc.cuerpo, tc.descripcion, tc.grano, tc.nombre_cafe, tc.precio,  tc.region, pc.cantidad "
			+ "from tabla_cafes as tc, producto_carrito as pc "
			+ "where pc.cafe_id = tc.id and pc.carrito_id = :carrito_id "
			+ "order by tc.precio desc";
	
	public static final String SQL_BORRAR_PRODUCTOS_CARRITO = 
			"delete from producto_carrito where carrito_id = :carrito_id";
	
	public static final String SQL_OBTENER_ID_USUARIO_POR_EMAIL = 
			"select id from usuario where email = :email";
	public static final String SQL_OBTENER_TOTAL_CAFES = 
			"select count(id) from tabla_cafes";

	public static final String SQL_OBTENER_TOTAL_CAFES_POR_TITULO = 
			"select count(id) from tabla_cafes where nombre_cafe like :nombre";
	


}
