package com.ignacio.tiendaCafe.serviciosREST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.ignacio.tiendaCafe.servicios.ServicioCafes;

//los servicios ahora en spring mvc se pueden hacer usando 
//controladores, que no devuelven una vista, sino json 

@RestController
public class ServicioRESTProductos {

	@Autowired
	private ServicioCafes servicioCafes;
	
	@RequestMapping("obtener-productos-json")
	public String obtenerProductosEnJSON(){
		return new Gson().toJson(servicioCafes.obtenerCafeParaListado());
	}
	
	@RequestMapping("obtener-detalles-cafe")
	public String obtenerDetallesCafe(@RequestParam("id") Integer id ) {
		return new Gson().toJson(servicioCafes.obtenerCafeVerDetallesPorId(id));
	}
	
}
