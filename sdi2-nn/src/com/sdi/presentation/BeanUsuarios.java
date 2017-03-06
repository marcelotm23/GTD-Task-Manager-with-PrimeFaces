package com.sdi.presentation;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.sdi.business.Services;
import com.sdi.business.UserService;
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

	
	public BeanUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(BeanUsuario usuario) {
		this.usuario = usuario;
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
				return "exito"; // Nos vamos a la vista de listado.
			}else{
				return "error";
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "error"; // Nos vamos a la vista de error.
		}
	}
	public String crearCuenta() {
		UserService userService;
		try {
			
			if(usuario!=null){
				userService = Services.getUserService();
				User userByLogin = userService.findLoggableUser(usuario.getLogin(),
						usuario.getPassword());
				usuario.setUsuario(userByLogin);			
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
		usuario = (BeanUsuario) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get(new String("usuario"));
//		// si no existe lo creamos e inicializamos
		if (usuario == null) {
			System.out.println("BeanUsuarios - No existia");
			usuario = new BeanUsuario();
		}
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put("usuario", usuario);
	}

	@PreDestroy
	public void end() {
		System.out.println("BeanAlumnos - PreDestroy");
	}
}
