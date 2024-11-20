package com.ignacio.tiendaCafe.servicioSetUp;

import java.io.IOException;
import java.net.URL;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ignacio.tiendaCafe.model.Cafe;
import com.ignacio.tiendaCafe.model.Categoria;
import com.ignacio.tiendaCafe.model.Pedido;
import com.ignacio.tiendaCafe.model.ProductoPedido;
import com.ignacio.tiendaCafe.model.Usuario;
import com.ignacio.tiendaCafe.model.estadosPedido.EstadosPedido;
import com.ignacio.tiendaCafe.servicios.ServicioCarrito;


@Service
@Transactional
public class SetUpJPAimpl implements SetUp{

	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private ServicioCarrito servicioCarrito;
	
	@Override
	public void setUp() {
		//vamos a usar una entidad para ver si 
		//ya hemos inicializado la base de datos
		//dicha entidad la llamamos SetUp
		
		com.ignacio.tiendaCafe.model.SetUp registoSetUp = null;
		
		try {
			//casi identico en sql a: select s.* from tabla_setup as s
			registoSetUp =
					(com.ignacio.tiendaCafe.model.SetUp)entityManager.createQuery("select s from SetUp as s").getSingleResult();
		} catch (Exception e) {
			System.out.println("no se encontro ningun registro de setup, comenzamos setup...");
		}
				
		
		if ( registoSetUp == null || !registoSetUp.isCompleto() ) {
			//vamos a crear unas categorias antes de nada
			Categoria granoMolido = new Categoria("grano molido", "categoria grano molido");
			Categoria capsulas = new Categoria("capsulas","categoria capsulas");
			Categoria granoSinMoler = new Categoria("grano SinMoler", "categoria grano SinMoler");
			entityManager.persist(granoMolido);
			entityManager.persist(capsulas);
			entityManager.persist(granoSinMoler);
			

			
			Cafe c1 = new Cafe("Cafe Nicaragua", "una descripcion ","grano medio", "Africa"," medio",12.5);
			c1.setCategoria(granoMolido);
			c1.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/1.jpg"));
			Cafe c2 = new Cafe("Cafe Peru", "una descripcion ","grano medio", "Africa"," alto",12.5);
			c2.setCategoria(capsulas);
			c2.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/2.jpg"));
			Cafe c3 = new Cafe("Cafe Colombia", "una descripcion ","grano medio", "Africa"," medio",12.5);
			c3.setCategoria(granoSinMoler);
			c3.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/3.jpg"));
			
			
			/*cafes de prueba para paginacion*/
			
			Cafe c4 = new Cafe("Cafe Colombia", "una descripcion ","grano medio", "Africa"," medio",12.5);
			c4.setCategoria(granoSinMoler);
			c4.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/3.jpg"));
			
			Cafe c5 = new Cafe("Cafe Colombia", "una descripcion ","grano medio", "Africa"," medio",12.5);
			c5.setCategoria(granoSinMoler);
			c5.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/3.jpg"));
			
			
			Cafe c6 = new Cafe("Cafe Colombia", "una descripcion ","grano medio", "Africa"," medio",12.5);
			c6.setCategoria(granoSinMoler);
			c6.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/3.jpg"));
			
			
			Cafe c7 = new Cafe("Cafe Colombia", "una descripcion ","grano medio", "Africa"," medio",12.5);
			c7.setCategoria(granoSinMoler);
			c7.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/3.jpg"));
			
			
			Cafe c8 = new Cafe("Cafe Colombia", "una descripcion ","grano medio", "Africa"," medio",12.5);
			c8.setCategoria(granoSinMoler);
			c8.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/3.jpg"));
			
			
			Cafe c9 = new Cafe("Cafe Colombia", "una descripcion ","grano medio", "Africa"," medio",12.5);
			c9.setCategoria(granoSinMoler);
			c9.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/3.jpg"));
			
			
			Cafe c10 = new Cafe("Cafe Colombia", "una descripcion ","grano medio", "Africa"," medio",12.5);
			c10.setCategoria(granoSinMoler);
			c10.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/3.jpg"));
			
			
			Cafe c11 = new Cafe("Cafe Colombia", "una descripcion ","grano medio", "Africa"," medio",12.5);
			c11.setCategoria(granoSinMoler);
			c11.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/3.jpg"));
			
			
			Cafe c12 = new Cafe("Cafe Colombia", "una descripcion ","grano medio", "Africa"," medio",12.5);
			c12.setCategoria(granoSinMoler);
			c12.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/3.jpg"));
			
			
			
			/*cafes de prueba para paginacion*/
			
			entityManager.persist(c1);
			entityManager.persist(c2);
			entityManager.persist(c3);
			entityManager.persist(c4);
			entityManager.persist(c5);
			entityManager.persist(c6);
			entityManager.persist(c7);
			entityManager.persist(c8);
			entityManager.persist(c9);
			entityManager.persist(c10);
			entityManager.persist(c11);
			entityManager.persist(c12);
			
			Usuario u1 = new Usuario("ares", "ares@gmail.com", "123", "65985125J","madrid","28891");
			Usuario u2 = new Usuario("Nacho", "ignacio.ifgo@gmail.com", "123", "65985126J","madrid","28891");
			Usuario u3 = new Usuario("juan", "juan@gmail.com", "123", "65985127J","madrid","28891");
			
			entityManager.persist(u1);
			entityManager.persist(u2);
			entityManager.persist(u3);
			
			//meto algunos productos por comdodidad
			servicioCarrito.agregarProducto(c1.getId(), u1.getId(), 2);
			servicioCarrito.agregarProducto(c2.getId(), u1.getId(), 1);
			
			servicioCarrito.agregarProducto(c2.getId(), u2.getId(), 1);
			servicioCarrito.agregarProducto(c2.getId(), u3.getId(), 1);
			
			//meter unos pedidos:
			Pedido pedido1 = new Pedido();
			pedido1.setNombreCompleto("Juan Martinez");
			pedido1.setDireccion("argüelles 12 28080 Madrid");
			pedido1.setProvincia("Madrid");
			pedido1.setTipoTarjeta("VISA");
			pedido1.setTitularTarjeta("Juan Martinez");
			pedido1.setNumeroTarjeta("3310 2201 5445 5555");
			
			pedido1.setUsuario(u1);
			pedido1.setEstado(EstadosPedido.COMPLETO.name());
			entityManager.persist(pedido1);
			ProductoPedido pp1 = new ProductoPedido();
			pp1.setPedido(pedido1);
			pp1.setCafe(c1);
			pp1.setCantidad(2);
			entityManager.persist(pp1);

			Pedido pedido2 = new Pedido();
			pedido2.setNombreCompleto("N. Completo 2");
			pedido2.setDireccion("Info Direccion 2");
			pedido2.setProvincia("Info Provincia 2");
			pedido2.setTipoTarjeta("VISA");
			pedido2.setNumeroTarjeta("4444 5555 6666 7777");
			pedido2.setTitularTarjeta("Info titular Tarketa 2");
			pedido2.setUsuario(u3);
			pedido2.setEstado(EstadosPedido.FINALIZADO.name());
			entityManager.persist(pedido2);
			ProductoPedido pp2 = new ProductoPedido();
			pp2.setPedido(pedido2);
			pp2.setCafe(c3);
			pp2.setCantidad(1);
			entityManager.persist(pp2);			
			
			
			Pedido pedido5 = new Pedido();
			pedido5.setNombreCompleto("N. Completo 5");
			pedido5.setDireccion("Info Direccion 5");
			pedido5.setProvincia("Info Provincia 5");
			pedido5.setTipoTarjeta("Discover");
			pedido5.setNumeroTarjeta("3333 4444 5555 6666");
			pedido5.setTitularTarjeta("Info titular Tarjeta 5");
			pedido5.setUsuario(u1);
			pedido5.setEstado(EstadosPedido.FINALIZADO.name());
			entityManager.persist(pedido5);
			ProductoPedido pp5 = new ProductoPedido();
			pp5.setPedido(pedido5);
			pp5.setCafe(c1);
			pp5.setCantidad(4);
			entityManager.persist(pp5);
			
			com.ignacio.tiendaCafe.model.SetUp setup = new com.ignacio.tiendaCafe.model.SetUp();
			setup.setCompleto(true);
			entityManager.persist(setup);
		}
	
	}//end setUp
	
	private byte[] leerBytesDeRutaOrigen(String rutaOrigen) {
		byte[] info = null;
		
		try {
			URL url = new URL(rutaOrigen);
			info = IOUtils.toByteArray(url);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("no pude leer de la ruta indicada");
		}
		return info;		
	}

}//end class
