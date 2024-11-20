package com.ignacio.tiendaCafe.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;


@Entity
@Table(name="tabla_cafes")
public class Cafe {
	
	//vamos a guardar la portada en base de datos
	@Lob
	@Column(name="imagen_portada")
	private byte[]imagenPortada;
	
	@Transient
	private int idCategoria;
	
	//asociaciones:
	@ManyToOne(optional = true)
	private Categoria categoria;
	
	
	@Size(min = 3, max = 40, message = "nombre debe tener entre 3 y 40 caracteres")
	@NotEmpty(message = "cafe no puede estar vacio")
	@Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ]{3,40}$", message = "nombre solo puede tener numeros, letras y espacios en blanco")
	@Column (name="nombre_cafe", length = 120)
	private String nombre;
	
	
	@Column(length = 650)
	private String descripcion;
	
	@Size(min = 3, max = 15, message = "grano debe tener entre 3 y 10 caracteres")
	@NotEmpty(message = "grano no puede estar vacio")
	@Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ]{3,40}$", message = "grano solo puede tener numeros, letras y espacios en blanco")
	private String grano;
	
	@Size(min = 3, max = 10, message = "region debe tener entre 3 y 10 caracteres")
	@NotEmpty(message = "region no puede estar vacio")
	@Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ]{3,40}$", message = "region solo puede tener numeros, letras y espacios en blanco")
	private String region;
	
	@Size(min = 3, max = 10, message = "cuerpo debe tener entre 3 y 10 caracteres")
	@NotEmpty(message = "cuerpo no puede estar vacio")
	@Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ]{3,40}$", message = "cuerpo solo puede tener numeros, letras y espacios en blanco")
	private String cuerpo;
	
	@NotNull(message = "debes insertar un precio")
	@Min( value = 1, message = "el precio minimo es 1 euro")
	@Max( value = 999, message = "el precio maximo es 999 euros")
	private double precio;
	@Transient
	private MultipartFile archivoSubido;
	
	@Id
	@GeneratedValue
	private long id;

	private Date fechaUltimaModificacion;
	
	
	public Cafe() {
		
	}

	public Cafe(String nombre, String descripcion, String grano, String region, String cuerpo, double precio) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.grano = grano;
		this.region = region;
		this.cuerpo = cuerpo;
		this.precio = precio;

	}
	


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getGrano() {
		return grano;
	}

	public void setGrano(String grano) {
		this.grano = grano;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public MultipartFile getArchivoSubido() {
		return archivoSubido;
	}

	public void setArchivoSubido(MultipartFile archivoSubido) {
		this.archivoSubido = archivoSubido;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getFechaUltimaModificacion() {
		return fechaUltimaModificacion;
	}

	public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public void setImagenPortada(byte[] imagenPortada) {
		this.imagenPortada = imagenPortada;
	}
	
	
	public byte[] getImagenPortada() {
		return imagenPortada;
	}

	@Override
	public String toString() {
		return "Cafe [nombre=" + nombre + ", descripcion=" + descripcion + ", grano=" + grano + ", region=" + region
				+ ", cuerpo=" + cuerpo + ", precio=" + precio+ "]";
	}
		

}
