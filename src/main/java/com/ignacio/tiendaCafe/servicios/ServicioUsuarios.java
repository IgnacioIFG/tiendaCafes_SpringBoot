package com.ignacio.tiendaCafe.servicios;

import com.ignacio.tiendaCafe.model.Usuario;

public interface ServicioUsuarios {

	void registrarUsuario(Usuario u);
	Usuario obtenerUsuarioPorEmailyPass(String email, String pass);
	boolean comprobarEmailExistente(String email);
}
