package com.ignacio.tiendaCafe.serviciosJPAimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ignacio.tiendaCafe.model.Categoria;
import com.ignacio.tiendaCafe.servicios.ServicioCategorias;



@Service
@Transactional
public class ServicioCategoriasJPAimpl implements ServicioCategorias {
	
	@PersistenceContext
	private EntityManager entityManager;

	
	@Override
	public List<Categoria> obtenerCategorias() {
		
		return entityManager.createQuery("select c from Categoria c").getResultList();
	}


	@Override
	public Categoria obtenerCategoriaPorId(int idCategoria) {
		
		return entityManager.find(Categoria.class, idCategoria);
	}
	
	
	

}
