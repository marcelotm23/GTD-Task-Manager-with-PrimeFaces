package com.sdi.presentation;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.sdi.business.AlumnosService;
import com.sdi.business.Services;
import com.sdi.business.UserService;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Alumno;
import com.sdi.model.User;

@ManagedBean(name = "controller")
@SessionScoped
public class BeanUsuarios implements Serializable {
	private static final long serialVersionUID = 55555L;
	// Se añade este atributo de entidad para recibir el usuario concreto
	// que se ha logeado
	// Es necesario inicializarlo para que al entrar desde el formulario de
	// AltaForm.xml se puedan
	// dejar los avalores en un objeto existente.
	// private Alumno alumno = new Alumno();
	// uso de inyección de dependencia
	@ManagedProperty(value = "#{usuario}")
	private BeanUsuario usuario;

	
	public BeanUsuario getAlumno() {
		return usuario;
	}

	public void setAlumno(BeanUsuario alumno) {
		this.usuario = alumno;
	}

	private User[] usuarios = null;

	public User[] getUsuarios() {
		return (usuarios);
	}

	public void setUsuarios(User[] usuarios) {
		this.usuarios = usuarios;
	}
	
	public String login() {
		UserService userService;
		try {
			
			if(usuario!=null){
				userService = Services.getUserService();
				User userByLogin = userService.findLoggableUser(usuario.getLogin(),
						usuario.getPassword());
				usuario.setUsuario(userByLogin);
	//				
					return "exito"; // Nos vamos a la vista de listado.
			}else{
				return "error";
			}

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
//		usuario = (BeanAlumno) FacesContext.getCurrentInstance()
//				.getExternalContext().getSessionMap().get(new String("alumno"));
//		// si no existe lo creamos e inicializamos
//		if (usuario == null) {
//			System.out.println("BeanAlumnos - No existia");
//			usuario = new BeanAlumno();
//		}
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put("alumno", usuario);
	}

	@PreDestroy
	public void end() {
		System.out.println("BeanAlumnos - PreDestroy");
	}
}
