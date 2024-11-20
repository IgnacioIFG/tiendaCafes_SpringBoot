package com.ignacio.tiendaCafe.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ignacio.tiendaCafe.model.ProductoCarrito;

public interface ServicioCarrito {
	void agregarProducto(long idProducto,long idUsuario, int cantidad);
	
	void quitarProducto(long idProducto, long idUsuario, int cantidad);
	
	List<Map<String, Object>> obtenerProductosCarritoUsuario(long idUsuario);
	
	ArrayList<ProductoCarrito> obtenerProductosCarritoUsuario2(long idUsuario);

	void actualizarCantidadProducto(long idProducto, long idUsuario, int cantidad);
	

}
