package com.sdi.presentation;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.sdi.business.Services;
import com.sdi.business.TaskService;
import com.sdi.business.UserService;
import com.sdi.business.exception.BusinessException;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Task;
import com.sdi.model.User;
import com.sdi.model.types.UserStatus;

@ManagedBean(name = "controller")
@SessionScoped
public class BeanUsuarios implements Serializable {
	private static final long serialVersionUID = 55555L;

	
	@ManagedProperty(value = "#{usuario}")
	private BeanUsuario usuario;
	@ManagedProperty(value = "#{tarea}")
	private BeanTarea tarea;
	private List<Task> tareas = null;
	
	public BeanUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(BeanUsuario usuario) {
		this.usuario = usuario;
	}

	

	public List<Task> getTareas() {
		return (tareas);
	}

	public void setTareas(List<Task> tareas) {
		this.tareas = tareas;
	}
	
	public String login() {
		UserService userService;
		try {
			
			if(usuario!=null){
				userService = Services.getUserService();
				User userByLogin = userService.findLoggableUser(usuario.getLogin(),
						usuario.getPassword());
				usuario.setUsuario(userByLogin);
				mostrarTareas();
				return "exito"; // Nos vamos a la vista de listado.
			}else{
				return "error";
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "error"; // Nos vamos a la vista de error.
		}
	}
	public String mostrarTareas() {
		TaskService taskService;
		try {
				taskService = Services.getTaskService();
				tareas=taskService.findInboxTasksByUserId(usuario.getId());
				System.out.println("TAREAS:"+tareas);
				return "exito"; 

		} catch (Exception e) {
			e.printStackTrace();
			return "error"; // Nos vamos a la vista de error.
		}

	}
	public String mostrarTareasHoy() {
		TaskService taskService;
		try {
				taskService = Services.getTaskService();
				tareas=taskService.findTodayTasksByUserId(usuario.getId());
				return "exito"; 

		} catch (Exception e) {
			e.printStackTrace();
			return "error"; // Nos vamos a la vista de error.
		}

	}
	public String mostrarTareasSemana() {
		TaskService taskService;
		try {
				taskService = Services.getTaskService();
				tareas=taskService.findWeekTasksByUserId(usuario.getId());
				return "exito"; 

		} catch (Exception e) {
			e.printStackTrace();
			return "error"; // Nos vamos a la vista de error.
		}

	}

	public String crearCuenta() {
		UserService userService;
		try {
			//userService = Services.getUserService();
			userService=Factories.services.createUserService();
			usuario.setStatus(UserStatus.ENABLED);
			userService.registerUser(usuario);
			return "exito"; 

		}
		catch(BusinessException b){
			FacesContext context = FacesContext.getCurrentInstance();
			ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msgs");
			String message = bundle.getString(b.getMessage());
	        context.addMessage("businessMesg", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",  message) );
	        
	        return null;
		}
		catch (Exception e) {
			e.printStackTrace();
			return "error"; // Nos vamos a la vista de error.
		}

	}
	
	public String editarTarea(){
		TaskService service;
		try {
			// Acceso a la implementacion de la capa de negocio
			// a trav��s de la factor��a
			service = Factories.services.createTaskService();
			// Recargamos la tarea seleccionado en la tabla de la base de datos
			// por si hubiera cambios.
			tarea = (BeanTarea) service.findTaskById(tarea.getId());
			return "exito"; // Nos vamos a la vista de Edición.

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
		System.out.println("BeanUsuarios - PostConstruct");
		// Buscamos el alumno en la sesión. Esto es un patrón factoría
		// claramente.
		usuario = (BeanUsuario) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get(new String("usuario"));
		tarea = (BeanTarea) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get(new String("tarea"));
//		// si no existe lo creamos e inicializamos
		if (usuario == null) {
			System.out.println("BeanUsuarios - No existia");
			usuario = new BeanUsuario();
			tarea = new  BeanTarea();
		}
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put("usuario", usuario);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
		.put("tarea", tarea);
	}

	@PreDestroy
	public void end() {
		System.out.println("BeanUsuarios - PreDestroy");
	}
}
