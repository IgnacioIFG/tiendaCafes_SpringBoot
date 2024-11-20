package com.ignacio.tiendaCafe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Usuario {

	@Size(min = 3, max = 40, message = "nombre debe tener entre 3 y 40 caracteres")
	@NotEmpty(message = "cafe no puede estar vacio")
	@Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ]{3,40}$", message = "nombre debe tener entre 3 y 40 caracteres ")
	@Column (name="nombre", length = 120)
	private String nombre;
	
	
	@Size(min = 5, max = 50, message = "El correo debe tener entre 5 y 50 caracteres")
	@NotEmpty(message = "El correo no puede estar vacío")
	@Pattern(regexp = "^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,}$", message = "Formato de correo inválido")
	@Column(unique = true)
	private String email;
	
	
	private String pass;
	
	@Size(min = 9, max = 9, message = "El DNI debe tener exactamente 9 caracteres")
	@NotEmpty(message = "El DNI no puede estar vacío")
	@Pattern(regexp = "^[0-9]{8}[A-Za-z]$", message = "El DNI debe tener 8 dígitos seguidos de una letra")
	private String dni;
	
	@Size(min = 3, max = 50, message = "La localidad debe tener entre 3 y 50 caracteres")
	@NotEmpty(message = "La localidad no puede estar vacía")
	@Pattern(regexp = "^[A-Za-záéíóúÁÉÍÓÚñÑ ]{3,50}$", message = "La localidad solo puede tener letras y espacios en blanco")
	@Column(name = "localidad_usuario", length = 50)
	private String localidad;
	
	@Size(min = 5, max = 5, message = "El código postal debe tener 5 dígitos")
	@NotEmpty(message = "El código postal no puede estar vacío")
	@Pattern(regexp = "^[0-9]{5}$", message = "El código postal solo puede contener 5 dígitos numéricos")
	private String codPostal;
	
	@OneToOne
	private Carrito carrito;
	
	
	@Id
	@GeneratedValue
	private long id;
	
	public Usuario() {
	}
	
	
	public Usuario(String nombre, String email, String pass, String dni, String localidad, String codPostal) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.pass = pass;
		this.dni = dni;
		this.localidad = localidad;
		this.codPostal = codPostal;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}


	public String getProvincia() {
		return localidad;
	}



	public void setProvincia(String localidad) {
		this.localidad = localidad;
	}


	public String getCodPostal() {
		return codPostal;
	}

	public void setCodPostal(String codPostal) {
		this.codPostal = codPostal;
	}
	
	


	public String getLocalidad() {
		return localidad;
	}


	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}


	public Carrito getCarrito() {
		return carrito;
	}


	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}


	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", email=" + email + ", pass=" + pass + ", dni=" + dni + ", localidad="
				+ localidad + ", codPostal=" + codPostal + ", id=" + id + "]";
	}



	
	
}
