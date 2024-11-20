package com.ignacio.tiendaCafe.serviciosREST;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ignacio.tiendaCafe.constantes.validaciones.ConstantesValidaciones;
import com.ignacio.tiendaCafe.model.Usuario;
import com.ignacio.tiendaCafe.servicios.ServicioUsuarios;
import com.ignacio.tiendaCafe.serviciosREST.respuestas.RespuestaLogin;

@RestController
public class ServicioRESTUsuarios {

	@Autowired
	private ServicioUsuarios servicioUsuarios;
	
	@RequestMapping("registrar-usuario-cliente")
	public String registrarUsuario(String nombre, String pass, String email, String dni, String localidad, String codPostal){
		
		if(servicioUsuarios.comprobarEmailExistente(email)) {
			return "email ya registrado";
		}
		
		//validar datos:
				Pattern patternNombre = Pattern.compile(ConstantesValidaciones.regExpNombreRegistroUsuario);
				Pattern patternEmail = Pattern.compile(ConstantesValidaciones.regExpEmailRegistroUsuario);
				Pattern patternPass = Pattern.compile(ConstantesValidaciones.regExpPassRegistroUsuario);
				Pattern patternDni = Pattern.compile(ConstantesValidaciones.regExpDniRegistroUsuario);
				Pattern patternLocalidad = Pattern.compile(ConstantesValidaciones.regExpLocalidadRegistroUsuario);
				Pattern patternCodigoPostal = Pattern.compile(ConstantesValidaciones.regExpCodPostalRegistroUsuario);
				
				
				
				Matcher matcherNombre = patternNombre.matcher(nombre);
				Matcher matcherEmail = patternEmail.matcher(email);
				Matcher matcherPass = patternPass.matcher(pass);
				Matcher matcherDni = patternDni.matcher(dni);
				Matcher matcherLocalidad = patternLocalidad.matcher(localidad);
				Matcher matcherCodigoPostal = patternCodigoPostal.matcher(codPostal);
				
				
				if ( ! matcherNombre.matches() ) {
					return "nombre incorrecto desde el lado del servidor";
				}
				if( ! matcherEmail.matches() ) {
					return "email incorrecto desde el lado del servidor";
				}
				if( ! matcherPass.matches()) {
					return "pass incorrecto desde el lado del server";
				}
				
				if( ! matcherDni.matches()) {
					return "dni incorrecto desde el lado del server";
				}
				if( ! matcherLocalidad.matches()) {
					return "localidad incorrecto desde el lado del server";
				}
				if( ! matcherCodigoPostal.matches()) {
					return "codigo postal incorrecto desde el lado del server";
				}
				
				

		Usuario u = new Usuario(nombre, email, pass, dni, localidad, codPostal);
		//TODO validar antes de registrar
		servicioUsuarios.registrarUsuario(u);
		return "Usuario registrado correctamente";
	}
	
	@RequestMapping("identificar-usuario")
	public RespuestaLogin identificarUsuario(String email, String pass, HttpServletRequest request){
		Usuario u = servicioUsuarios.obtenerUsuarioPorEmailyPass(email, pass);
		RespuestaLogin rl = null;
		if( u != null) {
			rl = new RespuestaLogin("ok", u.getNombre());
			//vamos a meter en sesion, la informacion del usuario que se ha identificado
			request.getSession().setAttribute("usuario", u);
		}else {
			rl = new RespuestaLogin("no-ok", "");
		}
		return rl;
	}
	
	@RequestMapping("cerrar-sesion-usuario")
	public String cerrarSesionUsuario(HttpServletRequest request) {
		request.getSession().invalidate();//esto elimina la sesion
		return "ok";
	}
	
	
}
