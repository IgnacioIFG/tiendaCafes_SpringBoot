package com.ignacio.tiendaCafe.serviciosJPAimpl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ignacio.tiendaCafe.constantesSQL.ConstantesSQL;
import com.ignacio.tiendaCafe.model.Usuario;
import com.ignacio.tiendaCafe.servicios.ServicioUsuarios;
import com.ignacio.tiendaCafe.utilidades.Utilidades;

@Service
@Transactional
public class ServicioUsuariosJPAimpl implements ServicioUsuarios{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void registrarUsuario(Usuario u) {
		entityManager.persist(u);
	}

	@Override
	public Usuario obtenerUsuarioPorEmailyPass(String email, String pass) {
		Query query = entityManager.createQuery(
				"select u from Usuario u where u.email = :email and u.pass = :pass ");
		query.setParameter("email", email);
		query.setParameter("pass", pass);
		List<Usuario> resultado = query.getResultList();
		if ( resultado.size() == 0 ) {
			return null;
		}else {
			return resultado.get(0);
		}						
	
	}

	@Override
	public boolean comprobarEmailExistente(String email) {
		Query query = entityManager.createNativeQuery(ConstantesSQL.SQL_OBTENER_ID_USUARIO_POR_EMAIL);
		query.setParameter("email", email);
		List<Map<String, Object>> res = Utilidades.procesaNativeQuery(query);		
		if( res.size() > 0) {
			return true;
		}else {
			return false;
		}
	}
	
}
