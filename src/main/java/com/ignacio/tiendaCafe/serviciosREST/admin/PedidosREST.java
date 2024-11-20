package com.ignacio.tiendaCafe.serviciosREST.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ignacio.tiendaCafe.servicios.ServicioPedidos;



@RestController
@RequestMapping("admin/")
public class PedidosREST {

	@Autowired
	private ServicioPedidos servicioPedidos;
	
	@RequestMapping("administracion-actualizar-estado-pedido")
	public String actualizarEstadoPedido(@RequestParam("id") Integer id, String estado) {
		
		servicioPedidos.actualizarEstadoPedido(id, estado);
		return "estado actualizado";
	}
	
}
