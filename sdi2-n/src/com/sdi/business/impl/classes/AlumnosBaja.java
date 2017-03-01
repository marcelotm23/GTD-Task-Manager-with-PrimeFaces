package com.sdi.business.impl.classes;

import com.sdi.business.exception.EntityNotFoundException;
import com.sdi.infrastructure.Factories;
import com.sdi.persistence.AlumnosDao;
import com.sdi.persistence.exception.NotPersistedException;

public class AlumnosBaja {

	public void delete(Long id) throws EntityNotFoundException {
		AlumnosDao dao = Factories.persistence.createAlumnoDao();
		try {
			dao.delete(id);
		}
		catch (NotPersistedException ex) {
			throw new EntityNotFoundException("Alumno no eliminado " + id, ex);
		}
	}
}
