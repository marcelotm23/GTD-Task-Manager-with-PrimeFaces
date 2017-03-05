package com.sdi.presentation;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.sdi.business.AlumnosService;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Alumno;

@ManagedBean(name = "controlleralum")
@SessionScoped
public class BeanAlumnos implements Serializable {
	private static final long serialVersionUID = 55555L;
	// Se añade este atributo de entidad para recibir el alumno concreto
	// selecionado de la tabla o de un formulario
	// Es necesario inicializarlo para que al entrar desde el formulario de
	// AltaForm.xml se puedan
	// dejar los avalores en un objeto existente.
	// private Alumno alumno = new Alumno();
	// uso de inyección de dependencia
	@ManagedProperty(value = "#{alumno}")
	private BeanAlumno alumno;

	public BeanAlumno getAlumno() {
		return alumno;
	}

	public void setAlumno(BeanAlumno alumno) {
		this.alumno = alumno;
	}

	private Alumno[] alumnos = null;

//	public BeanAlumnos() {
//		iniciaAlumno(null);
//	}

	public Alumno[] getAlumnos() {
		return (alumnos);
	}

	// public void setAlumno(Alumno alumno) {
	// this.alumno = alumno;
	// }
	// public Alumno getAlumno() {
	// return alumno;
	// }
	public void setAlumnos(Alumno[] alumnos) {
		this.alumnos = alumnos;
	}

	public void iniciaAlumno(ActionEvent event) {
		// alumno.setId(null);
		// alumno.setIduser("IdUser");
		// alumno.setNombre("Nombre");
		// alumno.setApellidos("Apellidos");
		// alumno.setEmail("email@domain.com");
		FacesContext facesContext = FacesContext.getCurrentInstance();
		// Obtenemos el archivo de propiedades correspondiente al idioma que
		// tengamos seleccionado y que viene envuelto en facesContext
		ResourceBundle bundle = facesContext.getApplication()
				.getResourceBundle(facesContext, "msgs");
		alumno.setId(null);
		alumno.setIduser(bundle.getString("valorDefectoUserId"));
		alumno.setNombre(bundle.getString("valorDefectoNombre"));
		alumno.setApellidos(bundle.getString("valorDefectoApellidos"));
		alumno.setEmail(bundle.getString("valorDefectoCorreo"));
	}

	public String listado() {
		AlumnosService service;
		try {
			// Acceso a la implementacion de la capa de negocio
			// a trav��s de la factor��a
//			service = Factories.services.createAlumnosService();
			// De esta forma le damos informaci��n a toArray para poder hacer el
			// casting a Alumno[]
//			alumnos = (Alumno[]) service.getAlumnos().toArray(new Alumno[0]);

			return "exito"; // Nos vamos a la vista listado.xhtml

		} catch (Exception e) {
			e.printStackTrace();
			return "error"; // Nos vamos la vista de error
		}

	}

	public String baja(Alumno alumno) {
		AlumnosService service;
		try {
			// Acceso a la implementacion de la capa de negocio
			// a trav��s de la factor��a
//			service = Factories.services.createAlumnosService();
			// Aliminamos el alumno seleccionado en la tabla
//			service.deleteAlumno(alumno.getId());
			// Actualizamos el javabean de alumnos inyectado en la tabla.
//			alumnos = (Alumno[]) service.getAlumnos().toArray(new Alumno[0]);
			return "exito"; // Nos vamos a la vista de listado.

		} catch (Exception e) {
			e.printStackTrace();
			return "error"; // Nos vamos a la vista de error
		}

	}

	public String edit() {
		AlumnosService service;
		try {
			// Acceso a la implementacion de la capa de negocio
			// a trav��s de la factor��a
//			service = Factories.services.createAlumnosService();
			// Recargamos el alumno seleccionado en la tabla de la base de datos
			// por si hubiera cambios.
//			alumno = (BeanAlumno) service.findById(alumno.getId());
			return "exito"; // Nos vamos a la vista de Edición.

		} catch (Exception e) {
			e.printStackTrace();
			return "error"; // Nos vamos a la vista de error.
		}

	}

	public String salva() {
		AlumnosService service;
		try {
			// Acceso a la implementacion de la capa de negocio
			// a trav��s de la factor��a
//			service = Factories.services.createAlumnosService();
			// Salvamos o actualizamos el alumno segun sea una operacion de alta
			// o de edici��n
//			if (alumno.getId() == null) {
//				service.saveAlumno(alumno);
//			} else {
//				service.updateAlumno(alumno);
//			}
//			// Actualizamos el javabean de alumnos inyectado en la tabla
//			alumnos = (Alumno[]) service.getAlumnos().toArray(new Alumno[0]);
			return "exito"; // Nos vamos a la vista de listado.

		} catch (Exception e) {
			e.printStackTrace();
			return "error"; // Nos vamos a la vista de error.
		}

	}

	// Se inicia correctamente el MBean inyectado si JSF lo hubiera crea
	// y en caso contrario se crea. (hay que tener en cuenta que es un Bean de
	// sesión)
	// Se usa @PostConstruct, ya que en el contructor no se sabe todavía si el
	// Managed
	// Bean
	// ya estaba construido y en @PostConstruct SI.
	@PostConstruct
	public void init() {
		System.out.println("BeanAlumnos - PostConstruct");
		// Buscamos el alumno en la sesión. Esto es un patrón factoría
		// claramente.
		alumno = (BeanAlumno) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get(new String("alumno"));
		// si no existe lo creamos e inicializamos
		if (alumno == null) {
			System.out.println("BeanAlumnos - No existia");
			alumno = new BeanAlumno();
		}
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put("alumno", alumno);
	}

	@PreDestroy
	public void end() {
		System.out.println("BeanAlumnos - PreDestroy");
	}
}
