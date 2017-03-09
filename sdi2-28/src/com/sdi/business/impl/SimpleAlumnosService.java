package com.sdi.business.impl;

import com.sdi.business.impl.classes.*;

import java.util.List;

import com.sdi.business.AlumnosService;
import com.sdi.business.exception.EntityAlreadyExistsException;
import com.sdi.business.exception.EntityNotFoundException;
import com.sdi.model.Alumno;

/**
 * Clase de implementación (una de las posibles) del interfaz de la fachada de
 * servicios
 * 
 * @author Enrique
 * 
 */
public class SimpleAlumnosService implements AlumnosService {

	@Override
	public List<Alumno> getAlumnos() throws Exception{
		return new AlumnosListado().getAlumnos();
	}

	@Override
	public void saveAlumno(Alumno alumno) throws EntityAlreadyExistsException {
		new AlumnosAlta().save(alumno);
	}

	@Override
	public void updateAlumno(Alumno alumno) throws EntityNotFoundException {
		new AlumnosUpdate().update(alumno);
	}

	@Override
	public void deleteAlumno(Long id) throws EntityNotFoundException {
		new AlumnosBaja().delete(id);
	}

	@Override
	public Alumno findById(Long id) throws EntityNotFoundException {
		return new AlumnosBuscar().find(id);
	}
}
