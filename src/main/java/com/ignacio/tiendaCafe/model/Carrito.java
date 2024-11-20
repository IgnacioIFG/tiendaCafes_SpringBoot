package com.ignacio.tiendaCafe.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Carrito {

	@OneToOne
	private Usuario usuario;
	
	@OneToMany(mappedBy = "carrito")
	private List<ProductoCarrito> productosCarrito = new ArrayList<ProductoCarrito>();
	
	private Date ultimoUso;
	
	@Id
	@GeneratedValue
	private long id;
	
	//en java si no existe ningun constructor en la clase
	//reasulta ser que a la clase se le incorpora uno vacio por defecto
	
	//la regla anterior no se cumple a la que haya al menos un 
	//constructor definido

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getUltimoUso() {
		return ultimoUso;
	}

	public void setUltimoUso(Date ultimoUso) {
		this.ultimoUso = ultimoUso;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<ProductoCarrito> getProductosCarrito() {
		return productosCarrito;
	}

	public void setProductosCarrito(List<ProductoCarrito> productosCarrito) {
		this.productosCarrito = productosCarrito;
	}
	
	
		
}
