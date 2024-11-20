package com.ignacio.tiendaCafe.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Pedido {
	
	@OneToMany(mappedBy = "pedido")
	private List<ProductoPedido> productosPedido =
		new ArrayList<ProductoPedido>();

	private String estado; 
	
	//se pide en paso intermedio
	private String codigoDescuento;
	private String detallesPedido;
	
	//precio final en paso intemedio
	private double precioFinal;
	
	//fin paso intermedio
	
	//se pide en paso 1
	
	private String nombreCompleto;
	private String direccion;
	private String pais;
	private String provincia;
	private String ciudad;
	private String codigoPostal;
	//fin paso 1
	
	//se pide en paso 2
	private String titularTarjeta;
	private String numeroTarjeta;
	private String tipoTarjeta;
	private String numSeguridad;
	private String fechaCaducidad;
	//fin paso 2
	
	@ManyToOne(targetEntity = Usuario.class, optional = false)
	private Usuario usuario;
	
	@Id
	@GeneratedValue
	private int id;

	
	
	
	public List<ProductoPedido> getProductosPedido() {
		return productosPedido;
	}

	public void setProductosPedido(List<ProductoPedido> productosPedido) {
		this.productosPedido = productosPedido;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCodigoDescuento() {
		return codigoDescuento;
	}

	public void setCodigoDescuento(String codigoDescuento) {
		this.codigoDescuento = codigoDescuento;
	}

	public String getDetallesPedido() {
		return detallesPedido;
	}

	public void setDetallesPedido(String detallesPedido) {
		this.detallesPedido = detallesPedido;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getTitularTarjeta() {
		return titularTarjeta;
	}

	public void setTitularTarjeta(String titularTarjeta) {
		this.titularTarjeta = titularTarjeta;
	}

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public String getTipoTarjeta() {
		return tipoTarjeta;
	}

	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumSeguridad() {
		return numSeguridad;
	}

	public void setNumSeguridad(String numSeguridad) {
		this.numSeguridad = numSeguridad;
	}

	public String getFechaCaducidad() {
		return fechaCaducidad;
	}

	public void setFechaCaducidad(String fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	public double getPrecioFinal() {
		return precioFinal;
	}

	public void setPrecioFinal(double precioFinal) {
		this.precioFinal = precioFinal;
	}

	
	/**
	 * calcula el precio total mediante una lambda
	 * @return
	 */
    public double calcularPrecioTotal() {
    	  System.out.println("Número de productos en el pedido: " + productosPedido.size());
    	System.out.println("ENTRA CALCULAR PRECIO TOTAL ");
    	
    	for (ProductoPedido p : productosPedido) {
			System.out.println("nombre del cafe " + p.getCafe().getNombre() + "precio " + p.getCafe().getPrecio() + "cantidad " +  p.getCantidad());
			
		}
    	
    	double n = productosPedido.stream().mapToDouble(p -> p.getCafe().getPrecio() * p.getCantidad()).sum();
        System.out.println("el numero es desde precio total " + n);
    	return n;
    }

    /**
     * metodo para calcular el precio con descuento
     * @param codigo
     * @return
     */
    public double calcularPrecioConDescuento(String codigo) {
    	
    	System.out.println("ENTRA CALCULAR PRECIO CODIGO");

        double total = calcularPrecioTotal();
        System.out.println("el total del pedido es sin descuento " + total);
        System.out.println("el numero del codigo es " +  codigo);
        if (codigo != null && codigo.equalsIgnoreCase("1234")) {
        	
        	System.out.println("el total con descuento es de " + total * 0.95);
            return total * 0.95; // Aplica un 5% de descuento
            
        }
        return total;
    }
	
	
	
	
}
