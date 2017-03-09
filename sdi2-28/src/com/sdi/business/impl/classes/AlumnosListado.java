package com.sdi.business.impl.classes;

import java.util.List;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Alumno;
import com.sdi.persistence.AlumnosDao;

/**
 * Esta clase pertenece a la capa de persistencia y ejecuta un proceso 
 * de negocio.
 * 
 * Si el problema a resolver fuese m??????s complejo habr?????? otras muchas clases de 
 * este estilo en esta capa. 
 * 
 * Las clases que forman la capa de negocio pueden necesitar acceder a la capa
 * de persistencia para resolver su cometido. Esta oculta los detalles de la 
 * tecnolog??????a de persistencia ofreciendo m??????todos del estilo: crear, borrer, 
 * actualizar y diversas consultas.
 * 
 */
public class AlumnosListado {

	public List<Alumno> getAlumnos() throws Exception {
		// Aqu?????? iria l??????gica de negocio que ejecutase alg??????n proceso ...
		//... en este caso el ejemplo estan sencillo que no hay nada que hacer
		
		// Acceso a la capa de persistencia a traves de su fachada
		// La fachada se obtiene de la factor??????a
		AlumnosDao dao = Factories.persistence.createAlumnoDao();
		return  dao.getAlumnos();

		// Aqu?????? podr??????a ir m??????s l??????gica de negocio que procesase los datos traidos 
		// de persistencia
		// ...
	}
}
