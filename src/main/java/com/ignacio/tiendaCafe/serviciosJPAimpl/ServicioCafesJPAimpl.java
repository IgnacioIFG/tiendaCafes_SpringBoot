package com.ignacio.tiendaCafe.serviciosJPAimpl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ignacio.tiendaCafe.constantesSQL.ConstantesSQL;
import com.ignacio.tiendaCafe.model.Cafe;
import com.ignacio.tiendaCafe.model.Categoria;
import com.ignacio.tiendaCafe.servicios.ServicioCafes;

 
@Service
@Transactional
public class ServicioCafesJPAimpl implements ServicioCafes{


	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void registrarCafe(Cafe c) {
		Categoria cate = entityManager.find(Categoria.class, c.getIdCategoria());
		c.setCategoria(cate);
		entityManager.persist(c);
		
	}

	@Override
	public List<Cafe> obtenerCafes() {
		return entityManager.createQuery("select c from Cafe c").getResultList();
	}

	@Override
	public void borrarCafe(long id) {
		Cafe c = entityManager.find(Cafe.class, id);
		entityManager.remove(c);
	}

	@Override
	public Cafe obtenerCafePorId(long id) {
		return entityManager.find(Cafe.class, id);
	}

	@Override
	public void actualizarCafe(Cafe c) {
		entityManager.merge(c);
	}

	@Override
	public Map<String, Object> obtenerCafeVerDetallesPorId(long id) {
		
		Query query = entityManager.createNativeQuery(ConstantesSQL.SQL_OBTENER_DETALLES_CAFE);
		query.setParameter("id", id);
		NativeQueryImpl nativeQuery = (NativeQueryImpl)query;
		nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		
		return (Map<String, Object>)nativeQuery.getSingleResult();

	}

	@Override
	public List<Map<String, Object>> obtenerCafeParaListado() {
		Query query = entityManager.createNativeQuery(ConstantesSQL.SQL_OBTENER_LISTADO_CAFES);
		NativeQueryImpl nativeQuery = (NativeQueryImpl)query;
		nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		
		return nativeQuery.getResultList();
	}

	@Override
	public List<Cafe> obtenerCafes(String nombre) {
		return entityManager.createQuery("select c from Cafe c where c.nombre like :nombre ").setParameter("nombre", "%" + nombre + "%" ).getResultList();
	}


	@Override
	public List<Cafe> obtenerCafes(String nombre, int comienzo, int resultadosPorPagina) {
		return entityManager.createQuery("select c from Cafe c where c.nombre like :nombre ").setParameter("nombre", "%" + nombre + "%" ).setFirstResult(comienzo).setMaxResults(resultadosPorPagina).getResultList();

	}

	@Override
	public int obtenerTotalCafes() {
		Query q = entityManager.createNativeQuery(ConstantesSQL.SQL_OBTENER_TOTAL_CAFES);
		return Integer.parseInt( q.getSingleResult().toString() );
	}
	
	@Override
	public int obtenerTotalCafes(String nombre) {
		Query q = entityManager.createNativeQuery(ConstantesSQL.SQL_OBTENER_TOTAL_CAFES_POR_TITULO);
		q.setParameter("nombre","%" + nombre + "%");
		return Integer.parseInt( q.getSingleResult().toString() );
	}
	
	

}
