package com.ignacio.tiendaCafe.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ignacio.tiendaCafe.model.Cafe;
import com.ignacio.tiendaCafe.servicios.ServicioCafes;
import com.ignacio.tiendaCafe.servicios.ServicioCategorias;

@Controller
@RequestMapping("admin/")
public class ControladorCafes {

	@Autowired
	private ServicioCafes servicioCafes;

	@Autowired
	private ServicioCategorias servicioCategorias;
	
	@RequestMapping("cafes")
	public String obtenerCafes(@RequestParam(name = "nombre", defaultValue = "") String nombre, @RequestParam(name="comienzo",defaultValue = "0") Integer comienzo, Model model) {
		List<Cafe> cafes =  servicioCafes.obtenerCafes(nombre,comienzo,6);
		int totalCafes = servicioCafes.obtenerTotalCafes(nombre);
		model.addAttribute("cafes",cafes);
		model.addAttribute("nombre",nombre);
		model.addAttribute("siguiente",comienzo + 6);
		model.addAttribute("anterior",comienzo - 6);
		model.addAttribute("total",totalCafes);
		return "admin/cafes";//esto es la jsp que se muestra
	}
	
	@RequestMapping("cafes-borrar")
	public String borrarCafe(String id, Model model) {
		//lo suyo seria validar la id antes de nada
		servicioCafes.borrarCafe(Integer.parseInt(id));
		return obtenerCafes("",0,model);
	}
	
	@RequestMapping("cafes-nuevo")
	public String nuevoCafe(Model model) {
		Cafe c = new Cafe();
		c.setPrecio(1);
		model.addAttribute("nuevoCafe", c);
		model.addAttribute("categorias", servicioCategorias.obtenerCategorias());
		return "admin/cafes-nuevo";
	}
	
	@RequestMapping("cafes-guardar-nuevo")
	public String guardarNuevoCafe(@ModelAttribute("nuevoCafe") @Valid Cafe nuevoCafe, BindingResult br, Model model, HttpServletRequest  request ) {
		
		if( br.hasErrors() ) {
			model.addAttribute("categorias", servicioCategorias.obtenerCategorias());
			return "admin/cafes-nuevo";
		}
		
		
		//se asigna el archivo subido
		try {
			nuevoCafe.setImagenPortada(nuevoCafe.getArchivoSubido().getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		servicioCafes.registrarCafe(nuevoCafe);
		return obtenerCafes("",0,model);
	}
	
	@RequestMapping("cafes-editar")
	public String editarCafe(String id, Model model ) {
		Cafe c = servicioCafes.obtenerCafePorId(Long.parseLong(id));
		model.addAttribute("cafeEditar",c);
		return "admin/cafes-editar";
	}
	
	@RequestMapping("cafes-guardar-cambios")
	public String guardarCambiosCafe(Cafe CafeEditar, Model model, 
			HttpServletRequest request) {
		//antes de nada lo suyo seria validar los datos introducidos

		
		//TODO falta bolver a asignar el archivo subido 
		servicioCafes.actualizarCafe(CafeEditar);
		
		return obtenerCafes("",0,model);
	}
	
}



