package com.ignacio.tiendaCafe.serviciosREST;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ignacio.tiendaCafe.constantes.validaciones.ConstantesValidaciones;
import com.ignacio.tiendaCafe.model.Usuario;
import com.ignacio.tiendaCafe.model.tiposExtra.ResumenPedido;
import com.ignacio.tiendaCafe.servicios.ServicioPedidos;



@RestController
public class ServicioRESTPedidos {

	@Autowired
	private ServicioPedidos servicioPedidos;
	
	
	@RequestMapping("realizar-pedido-paso-intermedio")
	public String realizarPedidoPasoIntermedio(String codigoDescuento, String detallesEnvio, HttpServletRequest request) {
		
		//validar codigo descuento:
		
		Pattern patternCodigoDescuento = Pattern.compile(ConstantesValidaciones.regExpCodigoDescuento);
		Matcher matcherCodigoDescuento= patternCodigoDescuento.matcher(codigoDescuento);
		
		
		if ( ! matcherCodigoDescuento.matches() ) {
			return "Codigo descuento desconocido desde servidor";
		}
		
		Usuario u = (Usuario)request.getSession().getAttribute("usuario");
		servicioPedidos.procesarPasoIntermedio(codigoDescuento, detallesEnvio, u.getId());
			
		return "ok";
		}
	
	@RequestMapping("realizar-pedido-paso1")
	public String realizarPedidoPaso1(String nombre, String direccion, String pais, String provincia, String ciudad, String codPostal, HttpServletRequest request) {
		
		
		//validar datos paso intermedio 1:
		Pattern patternNombre = Pattern.compile(ConstantesValidaciones.regExpNombreCompleto);
		Pattern patternDireccion = Pattern.compile(ConstantesValidaciones.regExpDireccion);
		Pattern patternPais= Pattern.compile(ConstantesValidaciones.regExpPais);
		Pattern patternProvincia = Pattern.compile(ConstantesValidaciones.regExpProvincia);
		Pattern patternCiudad = Pattern.compile(ConstantesValidaciones.regExpCiudad);
		Pattern patternCodigoPostal = Pattern.compile(ConstantesValidaciones.regExpCodPostalRegistroUsuario);
		
		
		
		Matcher matcherNombre = patternNombre.matcher(nombre);
		Matcher matcherDireccion = patternDireccion.matcher(direccion);
		Matcher matcherPais = patternPais.matcher(pais);
		Matcher matcherProvincia = patternProvincia.matcher(provincia);
		Matcher matcherCiudad = patternCiudad.matcher(ciudad);
		Matcher matcherCodigoPostal = patternCodigoPostal.matcher(codPostal);
		
		
		if ( ! matcherNombre.matches() ) {
			return "nombre incorrecto desde el lado del servidor";
		}
		if( ! matcherDireccion.matches() ) {
			return "direccion incorrecto desde el lado del servidor";
		}
		if( ! matcherPais.matches()) {
			return "pais incorrecto desde el lado del server";
		}
		
		if( ! matcherProvincia.matches()) {
			return "provincia incorrecto desde el lado del server";
		}
		if( ! matcherCiudad.matches()) {
			return "ciudad incorrecto desde el lado del server";
		}
		if( ! matcherCodigoPostal.matches()) {
			return "codigo postal incorrecto desde el lado del server";
		}
			
		Usuario u = (Usuario)request.getSession().getAttribute("usuario");
		servicioPedidos.procesarPaso1(nombre, direccion, pais, provincia, ciudad, codPostal, u.getId());		
		return "ok";
	}
	
	@RequestMapping("realizar-pedido-paso2")
	public ResumenPedido realizarPedidoPaso2(String tipoTarjeta, String numeroTarjeta, String titularTarjeta,String numeroSeguridad, String fechaCaducidad ,HttpServletRequest request) {
		
		Pattern patternNumeroTarjeta = Pattern.compile(ConstantesValidaciones.regExpNumeroTarjeta);
		Pattern patternTitularTarjetan = Pattern.compile(ConstantesValidaciones.regExpTitularTarjeta);
		Pattern patternNumeroSeguridad= Pattern.compile(ConstantesValidaciones.regExpNumSeguridad);
		Pattern patternFechaCaducidad = Pattern.compile(ConstantesValidaciones.regExpFechaCaducidad);
		
		
		Matcher matcherNumeroTarjeta = patternNumeroTarjeta.matcher(numeroTarjeta);
		Matcher matcherTitularTarjetan = patternTitularTarjetan.matcher(titularTarjeta);
		Matcher matcherNumeroSeguridad = patternNumeroSeguridad.matcher(numeroSeguridad);
		Matcher matcherFechaCaducidad = patternFechaCaducidad.matcher(fechaCaducidad);
		
		
		Usuario u = (Usuario)request.getSession().getAttribute("usuario");
		servicioPedidos.procesarPaso2(titularTarjeta, numeroTarjeta, tipoTarjeta, numeroSeguridad, fechaCaducidad, u.getId());
		ResumenPedido resumen = servicioPedidos.obtenerResumenDelPedido(u.getId());
		return resumen;
	}
	
	@RequestMapping("confirmar-pedido")
	public String confirmarPedido(HttpServletRequest request) {
		Usuario u = (Usuario)request.getSession().getAttribute("usuario");
		servicioPedidos.confirmarPedido(u.getId());
		return "pedido completado";
	}
	

	
}
