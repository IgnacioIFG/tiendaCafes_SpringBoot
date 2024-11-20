package com.ignacio.tiendaCafe.servicios;

import java.util.List;
import java.util.Map;

import com.ignacio.tiendaCafe.model.Cafe;

public interface ServicioCafes {
	
	void registrarCafe(Cafe c);
	
	List<Cafe> obtenerCafes();

	List<Cafe> obtenerCafes(String nombre);
	
	List<Cafe> obtenerCafes(String nombre, int comienzo, int resultadosPorPagina);
	
	void borrarCafe(long id);
	
	Cafe obtenerCafePorId(long id);
	
	void actualizarCafe(Cafe c);
	
	Map<String, Object> obtenerCafeVerDetallesPorId(long id);
	
	List<Map<String, Object>> obtenerCafeParaListado();

	int obtenerTotalCafes();
	
	int obtenerTotalCafes(String nombre);

}
