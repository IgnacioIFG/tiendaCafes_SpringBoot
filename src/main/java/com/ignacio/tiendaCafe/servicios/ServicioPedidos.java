package com.ignacio.tiendaCafe.servicios;

import java.util.List;

import com.ignacio.tiendaCafe.model.Pedido;
import com.ignacio.tiendaCafe.model.tiposExtra.ResumenPedido;

public interface ServicioPedidos {
	
	//parte administracion:
	List<Pedido> obtenerPedidos();
	Pedido obtenerPedidoPorId(int idPedido);	

	//parte cliente
	void procesarPasoIntermedio(String codigoDescuento, String detallesEnvio, long idUsuario);
	
	void procesarPaso1(String nombreCompleto, String direccion, String pais, String provincia, String ciudad, String codigoPostal, long idUsuario);
	
	void procesarPaso2(String titular, String numero, String tipoTarjeta, String numeroSeguridad, String fechaCaducidad, long idUsuario);
	
	ResumenPedido obtenerResumenDelPedido(long idUsuario);
	
	void confirmarPedido(long idUsuario);

	void actualizarEstadoPedido(int id, String estado);
	
	
}
