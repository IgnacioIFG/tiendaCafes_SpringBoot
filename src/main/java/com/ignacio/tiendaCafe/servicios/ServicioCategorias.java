package com.ignacio.tiendaCafe.servicios;

import java.util.List;

import com.ignacio.tiendaCafe.model.Categoria;

public interface ServicioCategorias {
	
	List<Categoria>obtenerCategorias();
	
	Categoria obtenerCategoriaPorId(int idCategoria);

}
