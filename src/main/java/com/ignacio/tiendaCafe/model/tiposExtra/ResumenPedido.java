package com.ignacio.tiendaCafe.model.tiposExtra;

import java.util.List;
import java.util.Map;

public class ResumenPedido {

	//productos que hay en el carrito
		private List<Map<String,Object>> cafes;
		
		//datos del paso 1 
		private String nombreCompleto;
		private String direccion;
		private String pais;
		private String provincia;
		private String ciudad;
		private String codigoPostal;

		
		//datos del paso 2
		private String titularTarjeta;
		private String numeroTarjeta;
		private String tipoTarjeta;
		private String numeroSeguridad;
		private String fechaCaducidad;
		public List<Map<String, Object>> getCafes() {
			return cafes;
		}
		public void setCafes(List<Map<String, Object>> cafes) {
			this.cafes = cafes;
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
		public String getNumeroSeguridad() {
			return numeroSeguridad;
		}
		public void setNumeroSeguridad(String numeroSeguridad) {
			this.numeroSeguridad = numeroSeguridad;
		}
		public String getFechaCaducidad() {
			return fechaCaducidad;
		}
		public void setFechaCaducidad(String fechaCaducidad) {
			this.fechaCaducidad = fechaCaducidad;
		}
		
		//si no hay ningun constructor, java incorpora uno vacio, que no pide nada, por defecto
		

		
		
}
