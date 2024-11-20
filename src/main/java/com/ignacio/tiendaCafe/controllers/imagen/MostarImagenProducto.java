package com.ignacio.tiendaCafe.controllers.imagen;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ignacio.tiendaCafe.servicios.ServicioCafes;



@Controller
public class MostarImagenProducto {
	
	@Autowired
	private ServicioCafes servicioCafes;
	
	@RequestMapping("mostrar_imagen")
	public void mostrarImagen(String id, HttpServletResponse response) throws IOException {
		byte[]info=servicioCafes.obtenerCafePorId(Long.parseLong(id)).getImagenPortada();
		if(info==null) {
			return;
		}
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		response.getOutputStream().write(info);
		response.getOutputStream().close();
	}
	
}
