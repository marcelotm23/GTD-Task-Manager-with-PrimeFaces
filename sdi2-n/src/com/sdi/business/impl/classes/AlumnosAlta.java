package com.sdi.business.impl.classes;

import com.sdi.business.exception.EntityAlreadyExistsException;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Alumno;
import com.sdi.persistence.AlumnosDao;
import com.sdi.persistence.exception.AlreadyPersistedException;

public class AlumnosAlta {

	public void save(Alumno alumno) throws EntityAlreadyExistsException {
		AlumnosDao dao = Factories.persistence.createAlumnoDao();
		try {
			dao.save(alumno);
		}
		catch (AlreadyPersistedException ex) {
			throw new EntityAlreadyExistsException("Alumno ya existe " + alumno, ex);
		}
	}

}
