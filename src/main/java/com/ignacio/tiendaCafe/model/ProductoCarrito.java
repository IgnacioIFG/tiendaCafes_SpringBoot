package com.ignacio.tiendaCafe.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class ProductoCarrito {

	@OneToOne
	@JoinColumn(referencedColumnName = "id")
	private Cafe Cafe;
	
	@ManyToOne
	@JoinColumn(name = "carrito_id")
	private Carrito carrito;
	
	private int cantidad;

	
	@Id
	@GeneratedValue
	private int id;

	public Cafe getCafe() {
		return Cafe;
	}

	public void setCafe(Cafe Cafe) {
		this.Cafe = Cafe;
	}

	public Carrito getCarrito() {
		return carrito;
	}

	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
