package com.sdi.business.impl.classes;

import com.sdi.business.exception.EntityNotFoundException;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Alumno;
import com.sdi.persistence.AlumnosDao;

public class AlumnosBuscar {

	public Alumno find(Long id) throws EntityNotFoundException {
		AlumnosDao dao = Factories.persistence.createAlumnoDao();
		Alumno a = dao.findById(id);
		if ( a == null) {
			throw new EntityNotFoundException("No se ha encontrado el alumno");
		}
		
		return a;
	}

}
