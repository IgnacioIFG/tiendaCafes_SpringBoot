package com.ignacio.tiendaCafe.serviciosJPAimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ignacio.tiendaCafe.constantesSQL.ConstantesSQL;
import com.ignacio.tiendaCafe.model.Carrito;
import com.ignacio.tiendaCafe.model.Pedido;
import com.ignacio.tiendaCafe.model.ProductoCarrito;
import com.ignacio.tiendaCafe.model.ProductoPedido;
import com.ignacio.tiendaCafe.model.Usuario;
import com.ignacio.tiendaCafe.model.estadosPedido.EstadosPedido;
import com.ignacio.tiendaCafe.model.tiposExtra.ResumenPedido;
import com.ignacio.tiendaCafe.servicios.ServicioCarrito;
import com.ignacio.tiendaCafe.servicios.ServicioPedidos;

@Service
@Transactional
public class ServicioPedidosJPAimpl implements ServicioPedidos {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private ServicioCarrito servicioCarrito;

	// en cuanto el usuario completa el paso 1
	// generamos, si no existe ya, un unico pedido incompleto para el usuario
	// sobre el que vamos a preparar los datos de la compra
	private Pedido obtenerPedidoActual(long idUsuario) throws Exception {
		Usuario usuario = entityManager.find(Usuario.class, idUsuario);
		Object pedidoEnProceso = null;

		List<Pedido> resultadoConsulta = entityManager
				.createQuery("select p from Pedido p where p.estado = :estado and p.usuario.id = :usuario_id ")
				.setParameter("estado", EstadosPedido.INCOMPLETO.name()).setParameter("usuario_id", idUsuario)
				.getResultList();

		if (resultadoConsulta.size() == 1) {
			pedidoEnProceso = resultadoConsulta.get(0);
		} else if (resultadoConsulta.size() > 1) {
			throw new Exception("se ha colado mas de un pedido incompleto para el mismo usuario");
		}

		Pedido pedido = null;
		if (pedidoEnProceso != null) {
			pedido = (Pedido) pedidoEnProceso;
		} else {
			pedido = new Pedido();
			pedido.setEstado(EstadosPedido.INCOMPLETO.name());
			pedido.setUsuario(usuario);
		}
		return pedido;
	}

	@Override
	public void procesarPasoIntermedio(String codigoDescuento, String detallesEnvio, long idUsuario) {

		try {
			Pedido p = obtenerPedidoActual(idUsuario);
			p.setCodigoDescuento(codigoDescuento);
			p.setDetallesPedido(detallesEnvio);
			
			double precioDesc= calcularPrecioTotalCarro(idUsuario, codigoDescuento);
			p.setPrecioFinal(precioDesc);
			
			entityManager.merge(p);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public double calcularPrecioTotalCarro(long idUsuario, String codigoDescuento) {    
	    double precioFinal = 0;
	    
	    try {
	        Usuario usuario = entityManager.find(Usuario.class, idUsuario);
	        
	        ArrayList<ProductoCarrito> productosCarrito = new ArrayList<>(servicioCarrito.obtenerProductosCarritoUsuario2(idUsuario));
	        
	        for (ProductoCarrito p : productosCarrito) {
	            String nombreCafe = p.getCafe().getNombre();
	            int cantidad = p.getCantidad();
	            
	            System.out.println("nombre del cafe: " + nombreCafe + " cantidad: " + cantidad);
	            
	            precioFinal += p.getCafe().getPrecio() * cantidad;
	        }
	        
	        if(codigoDescuento.equalsIgnoreCase("1234")) {
	        	return precioFinal * 0.95; // Aplica un 5% de descuento
	        }
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return precioFinal;
	}

	
	

	@Override
	public void procesarPaso1(String nombreCompleto, String direccion, String pais, String provincia, String ciudad,
			String codigoPostal, long idUsuario) {
		try {
			Pedido p = obtenerPedidoActual(idUsuario);
			p.setNombreCompleto(nombreCompleto);
			p.setDireccion(direccion);
			p.setProvincia(provincia);
			p.setPais(pais);
			p.setCiudad(ciudad);
			p.setCodigoPostal(codigoPostal);

			entityManager.merge(p);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("hubo un problema obteniendo el pedido actual");
		}

	}

	@Override
	public void procesarPaso2(String titular, String numero, String tipoTarjeta, String numeroSeguridad,
			String fechaCaducidad, long idUsuario) {

		try {
			Pedido p = obtenerPedidoActual(idUsuario);
			p.setTitularTarjeta(titular);
			p.setNumeroTarjeta(numero);
			p.setTipoTarjeta(tipoTarjeta);
			p.setNumSeguridad(numeroSeguridad);
			p.setFechaCaducidad(fechaCaducidad);
			entityManager.merge(p);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public ResumenPedido obtenerResumenDelPedido(long idUsuario) {

		ResumenPedido resumen = new ResumenPedido();
		try {
			Pedido p = obtenerPedidoActual(idUsuario);
			// paso 1
			resumen.setNombreCompleto(p.getNombreCompleto());
			resumen.setDireccion(p.getDireccion());
			resumen.setProvincia(p.getProvincia());
			resumen.setPais(p.getPais());
			resumen.setCiudad(p.getCiudad());
			resumen.setCodigoPostal(p.getCodigoPostal());
			// paso 2
			resumen.setTipoTarjeta(p.getTipoTarjeta());
			resumen.setTitularTarjeta(p.getTitularTarjeta());
			resumen.setNumeroTarjeta(p.getNumeroTarjeta());
			resumen.setNumeroSeguridad(p.getNumSeguridad());
			resumen.setFechaCaducidad(p.getFechaCaducidad());

			// incluimos los productos del carrito en el resumen
			resumen.setCafes(servicioCarrito.obtenerProductosCarritoUsuario(idUsuario));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resumen;
	}

	@Override
	public void confirmarPedido(long idUsuario) {
		try {
			Pedido pedido = obtenerPedidoActual(idUsuario);
			Usuario usuario = entityManager.find(Usuario.class, idUsuario);
			Carrito carrito = usuario.getCarrito();
			// pasar todos los productos del carrito al pedido
			if (carrito != null && carrito.getProductosCarrito().size() > 0) {
				for (ProductoCarrito pc : carrito.getProductosCarrito()) {
					ProductoPedido pp = new ProductoPedido();
					pp.setCantidad(pc.getCantidad());
					pp.setCafe(pc.getCafe());
					pedido.getProductosPedido().add(pp);
					pp.setPedido(pedido);
					entityManager.persist(pp);
				}
			}
			// borrar los productos del carrito del usuario
			Query query = entityManager.createNativeQuery(ConstantesSQL.SQL_BORRAR_PRODUCTOS_CARRITO);
			query.setParameter("carrito_id", carrito.getId());
			query.executeUpdate();
			// finalizar pedido
			pedido.setEstado(EstadosPedido.COMPLETO.name());
			entityManager.merge(pedido);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}//fin confirmar pedido

	@Override
	public List<Pedido> obtenerPedidos() {
		return entityManager.createQuery("select p from Pedido p order by p.id desc").getResultList();		
	}

	@Override
	public Pedido obtenerPedidoPorId(int idPedido) {
		return entityManager.find(Pedido.class, idPedido);
	}
	
	@Override
	public void actualizarEstadoPedido(int id, String estado) {
		Pedido p = entityManager.find(Pedido.class, id);
		p.setEstado(estado);
		entityManager.merge(p);
	}
	
	


}
