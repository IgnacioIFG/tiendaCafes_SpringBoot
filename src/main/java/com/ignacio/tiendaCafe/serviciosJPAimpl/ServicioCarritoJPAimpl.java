package com.ignacio.tiendaCafe.serviciosJPAimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ignacio.tiendaCafe.constantesSQL.ConstantesSQL;
import com.ignacio.tiendaCafe.model.Cafe;
import com.ignacio.tiendaCafe.model.Carrito;
import com.ignacio.tiendaCafe.model.ProductoCarrito;
import com.ignacio.tiendaCafe.model.Usuario;
import com.ignacio.tiendaCafe.servicios.ServicioCarrito;
import com.ignacio.tiendaCafe.utilidades.Utilidades;

@Service
@Transactional
public class ServicioCarritoJPAimpl implements ServicioCarrito {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void agregarProducto(long idProducto, long idUsuario, int cantidad) {
		Usuario usuario = entityManager.find(Usuario.class, idUsuario);
		Carrito carrito = usuario.getCarrito();
		//si el usuario no tiene asociado un carrito, pues creamos un carrito
		//para el usuario
		if(carrito == null) {
			carrito = new Carrito();
			carrito.setUsuario(usuario);
			usuario.setCarrito(carrito);
			entityManager.persist(carrito);
		}

		boolean productoEnCarrito = false;
		for(ProductoCarrito pc : carrito.getProductosCarrito()) {
			if(pc.getCafe().getId() == idProducto) {
				productoEnCarrito = true;
				//aprovechamos e incrementamos su cantidad
				pc.setCantidad(pc.getCantidad() + cantidad);
				entityManager.merge(pc);
			}
		}//end for
		if( ! productoEnCarrito) {
			ProductoCarrito pc = new ProductoCarrito();
			pc.setCarrito(carrito);
			pc.setCafe(entityManager.find(Cafe.class, idProducto));
			pc.setCantidad(cantidad);
			entityManager.persist(pc);
		}
	}//end agregarProductoCarrito
	
	
	@Override
	public void quitarProducto(long idProducto, long idUsuario, int cantidad) {
		Usuario usuario = entityManager.find(Usuario.class, idUsuario);
		Carrito carrito = usuario.getCarrito();
		
	    if (carrito != null) {
	        for (ProductoCarrito pc : carrito.getProductosCarrito()) {
	            if (pc.getCafe().getId() == idProducto) {
	                int nuevaCantidad = pc.getCantidad() - cantidad;
	                
	                if (nuevaCantidad > 0) {
	                	
	                    pc.setCantidad(nuevaCantidad);
	                    entityManager.merge(pc);
	                } else {

	                    carrito.getProductosCarrito().remove(pc);
	                    entityManager.remove(pc);
	                }
	                break; 
	            }
	        }
	    }
		
	}
	
	@Override
	public void actualizarCantidadProducto(long idProducto, long idUsuario, int cantidad) {
		Usuario usuario = entityManager.find(Usuario.class, idUsuario);
		Carrito carrito = usuario.getCarrito();
		
		if (carrito != null) {
	        for (ProductoCarrito pc : carrito.getProductosCarrito()) {
	            if (pc.getCafe().getId() == idProducto) {
	                int nuevaCantidad = cantidad;
	                
	                if (nuevaCantidad > 0) {
	                	
	                    pc.setCantidad(nuevaCantidad);
	                    entityManager.merge(pc);
	                } else {

	                    carrito.getProductosCarrito().remove(pc);
	                    entityManager.remove(pc);
	                }
	                break; 
	            }
	        }
	    }
		
		
	}
	
	

	@Override
	public List<Map<String, Object>> obtenerProductosCarritoUsuario(long idUsuario) {
		Usuario usuario = entityManager.find(Usuario.class, idUsuario);
		Carrito carrito = usuario.getCarrito();
		List<Map<String, Object>> res = new ArrayList<Map<String,Object>>();
		if (carrito != null) {
			//createNativeQuery es para poder usar SQL
			Query query = entityManager.createNativeQuery(
					ConstantesSQL.SQL_OBTENER_CAFES_CARRITO);
			query.setParameter("carrito_id", carrito.getId());
			
			res = Utilidades.procesaNativeQuery(query);			
		}		
		return res;
	}


	@Override
	public ArrayList<ProductoCarrito> obtenerProductosCarritoUsuario2(long idUsuario) {
	    Usuario usuario = entityManager.find(Usuario.class, idUsuario);
	    Carrito carrito = usuario.getCarrito();

	    if (carrito != null) {
	        // Usamos JPQL para obtener directamente los productos del carrito
	        List<ProductoCarrito> resultado = entityManager.createQuery(
	            "SELECT pc FROM ProductoCarrito pc WHERE pc.carrito.id = :carritoId", 
	            ProductoCarrito.class)
	            .setParameter("carritoId", carrito.getId())
	            .getResultList();
	        
	        // Convertimos el resultado a un ArrayList
	        return new ArrayList<>(resultado);
	    }

	    return new ArrayList<>();
	}







}
