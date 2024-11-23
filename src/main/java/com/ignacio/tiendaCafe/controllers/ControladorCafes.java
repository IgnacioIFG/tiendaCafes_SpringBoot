package com.ignacio.tiendaCafe.controllers;

import java.io.IOException;
import java.util.Date;
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
import com.ignacio.tiendaCafe.model.Categoria;
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
		model.addAttribute("categorias", servicioCategorias.obtenerCategorias()); 
		return "admin/cafes-editar";
	}
	
	
	
	@RequestMapping("cafes-guardar-cambios")
	public String guardarCambiosCafe(@ModelAttribute("cafeEditar") @Valid Cafe cafeEditar, BindingResult bindingResult, Model model,
			HttpServletRequest request) {
		
		if (bindingResult.hasErrors()) {
            model.addAttribute("categorias", servicioCategorias.obtenerCategorias());
            return "admin/cafes-editar";
        }
			 
		 try {
			 
			System.out.println( "id del cafe para encontrarlo "+ cafeEditar.getId());
			 
			 Cafe cafeOriginal = servicioCafes.obtenerCafePorId(cafeEditar.getId());
			
	            
	            if (cafeOriginal == null) {
	                throw new RuntimeException("No se encontró el cafe con ID: " + cafeEditar.getId());
	            }

	            cafeOriginal.setNombre(cafeEditar.getNombre());
	            cafeOriginal.setGrano(cafeEditar.getGrano());
	            cafeOriginal.setRegion(cafeEditar.getRegion());
	            cafeOriginal.setCuerpo(cafeEditar.getCuerpo());
	            cafeOriginal.setDescripcion(cafeEditar.getDescripcion());
	            cafeOriginal.setPrecio(cafeEditar.getPrecio());

	            if (cafeEditar.getIdCategoria() != 0) {
	                Categoria categoria = servicioCategorias.obtenerCategoriaPorId(cafeEditar.getIdCategoria());
	                if (categoria != null) {
	                    cafeOriginal.setCategoria(categoria);
	                } else {
	                    model.addAttribute("error", "Categoría no encontrada");
	                    return "admin/cafes-editar";
	                }
	            }

	            if (cafeEditar.getArchivoSubido() != null && !cafeEditar.getArchivoSubido().isEmpty()) {
	                cafeOriginal.setImagenPortada(cafeEditar.getArchivoSubido().getBytes());
	            }

	            cafeOriginal.setFechaUltimaModificacion(new Date());

	            servicioCafes.actualizarCafe(cafeOriginal);

	            model.addAttribute("mensaje", "cafe actualizado con éxito");
	        } catch (Exception e) {
	            model.addAttribute("error", "Error al actualizar el cafe: " + e.getMessage());
	            e.printStackTrace();
	            return "admin/cafes-editar";
	        }

	        return "redirect:/admin/cafes";

	}
	
		
}



