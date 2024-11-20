package com.ignacio.tiendaCafe.utilidades;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

public class Utilidades {
	public static List<Map<String, Object>> procesaNativeQuery( Query query ){
		NativeQueryImpl nativeQuery = (NativeQueryImpl) query;
		nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		return nativeQuery.getResultList();		
	}
}
