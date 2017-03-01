package com.sdi.business.impl.classes;

import com.sdi.business.exception.EntityNotFoundException;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Alumno;
import com.sdi.persistence.AlumnosDao;
import com.sdi.persistence.exception.NotPersistedException;

public class AlumnosUpdate {

	public void update(Alumno alumno) throws EntityNotFoundException {
		AlumnosDao dao = Factories.persistence.createAlumnoDao();
		try {
			dao.update(alumno);
		}
		catch (NotPersistedException ex) {
			throw new EntityNotFoundException("Alumno no eliminado " + alumno, ex);
		}
	}

}
