package com.sdi.persistence.impl;


import com.sdi.persistence.AlumnosDao;
import com.sdi.persistence.PersistenceFactory;

/**
 * Implementaci??????n de la factoria que devuelve implementaci??????n de la capa
 * de persistencia con Jdbc 
 * 
 * @author alb
 *
 */
public class SimplePersistenceFactory implements PersistenceFactory {

	@Override
	public AlumnosDao createAlumnoDao() {
		return new AlumnoJdbcDAO();
	}

}
