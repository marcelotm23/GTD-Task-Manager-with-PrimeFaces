package com.sdi.presentation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.MenuActionEvent;
import org.primefaces.model.menu.MenuItem;

import alb.util.date.DateUtil;

import com.sdi.business.AdminService;
import com.sdi.business.Services;
import com.sdi.business.TaskService;
import com.sdi.business.UserService;
import com.sdi.business.exception.BusinessException;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Category;
import com.sdi.model.Task;
import com.sdi.model.User;
import com.sdi.model.types.UserStatus;
import com.sdi.presentation.util.MessageToUser;
import com.sdi.presentation.util.ReinicioBBDD;

@ManagedBean(name = "controller")
@SessionScoped
public class BeanControlador implements Serializable {
	private static final long serialVersionUID = 55555L;

	
	@ManagedProperty(value = "#{usuario}")
	private BeanUsuario usuario;
	@ManagedProperty(value = "#{tarea}")
	private BeanTarea tarea;
	private List<Task> tareas = null;
	private List<Task> tareasFiltradas = null;
	private List<Category> categorias = null;
	private Date today=DateUtil.today();
	private List<User> usuarios = null;


	private boolean verFinalizadas;
	
	public boolean isVerFinalizadas() {
		return verFinalizadas;
	}

	public void setVerFinalizadas(boolean verFinalizadas) {
		this.verFinalizadas = verFinalizadas;
	}

	public BeanUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(BeanUsuario usuario) {
		this.usuario = usuario;
	}

	public List<Category> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Category> categorias) {
		this.categorias = categorias;
	}

	public List<Task> getTareas() {
		return (tareas);
	}

	public void setTareas(List<Task> tareas) {
		this.tareas = tareas;
	}
	
	public String cargarTareasCategoria(ActionEvent event){
		TaskService taskService;
		try {
				resetTablaTareas();
				MenuItem menuItem = ((MenuActionEvent) event).getMenuItem();
			    Long catId = Long.parseLong(menuItem.getParams().get("catSelecId").get(0));
				taskService = Services.getTaskService();
				tareas=taskService.findTasksByCategoryId(catId);
				return "exito"; 

		} catch (Exception e) {
			e.printStackTrace();
			return "error"; // Nos vamos a la vista de error.
		}
	} 
	
	public String mostrarTareas() {
		TaskService taskService;
		try {
				resetTablaTareas();
				taskService = Services.getTaskService();
				tareas=taskService.findInboxTasksByUserId(usuario.getId());
				return "exito"; 

		} catch (Exception e) {
			e.printStackTrace();
			return "error"; // Nos vamos a la vista de error.
		}

	}
	public String mostrarTareasHoy() {
		TaskService taskService;
		try {
				resetTablaTareas();
				taskService = Services.getTaskService();
				tareas=taskService.findTodayTasksByUserId(usuario.getId());
				
				return "exito"; 

		} catch (Exception e) {
			e.printStackTrace();
			return "error"; // Nos vamos a la vista de error.
		}

	}

	public void resetTablaTareas() {
		DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("formlistado:tablalistado");
		if(dataTable!=null){
			dataTable.resetValue();
			//dataTable.reset();
		}
		
	}
	public String mostrarTareasSemana() {
		TaskService taskService;
		try {
				resetTablaTareas();
				taskService = Services.getTaskService();
				tareas=taskService.findWeekTasksByUserId(usuario.getId());
				return "exito"; 

		} catch (Exception e) {
			e.printStackTrace();
			return "error"; // Nos vamos a la vista de error.
		}

	}
	public String verTareasFinalizadas() {
		TaskService taskService;
		try {
				resetTablaTareas();
				taskService = Services.getTaskService();
				List<Task> tareasFinalizadas=taskService.findFinishedInboxTasksByUserId(usuario.getId());
				if(verFinalizadas){
					tareas.addAll(tareasFinalizadas);
				}else{
					tareas.removeAll(tareasFinalizadas);
				}
				
				return "exito"; 

		} catch (Exception e) {
			e.printStackTrace();
			return "error"; // Nos vamos a la vista de error.
		}

	}
	public String cargarCategoriasUsuario(){
		TaskService taskService;
		try {
				taskService = Services.getTaskService();
				categorias=taskService.findCategoriesByUserId(usuario.getId());
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
			MessageToUser.writeGrowlMessageERROR(b.getMessage());
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
	public String finalizarTarea(){
		TaskService taskService;
		try {
			// Acceso a la implementacion de la capa de negocio
			// a trav��s de la factoría
			taskService = Factories.services.createTaskService();
			taskService.markTaskAsFinished(tarea.getId());
			tareas=taskService.findInboxTasksByUserId(usuario.getId());
			return "exito"; // Actualizamos el listado de tareas.

		} catch (Exception e) {
			e.printStackTrace();
			return "error"; // Nos vamos a la vista de error.
		}
	}
	public String salvaTarea() {
		TaskService service;
		try {
			// Acceso a la implementacion de la capa de negocio
			// a trav��s de la factor��a
			service = Factories.services.createTaskService();
			// Salvamos o actualizamos la tarea segun sea una operacion de alta
			// o de edición
			if (tarea.getId() == null) {
				service.createTask(tarea);
			} else {
				service.updateTask(tarea);
			}
			// Actualizamos el javabean de tareas inyectado en la tabla
			tareas=service.findInboxTasksByUserId(usuario.getId());
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
			usuario=new BeanUsuario();
			usuario.setUsuario((User) FacesContext.getCurrentInstance()
					.getExternalContext().getSessionMap().get(new String("LOGGEDIN_USER")));
		}
		if(tarea == null){
			tarea = new  BeanTarea();
		}
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put("usuario", usuario);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
		.put("tarea", tarea);
		mostrarTareas();
		listadoUsuarios();
	}

	@PreDestroy
	public void end() {
		System.out.println("BeanUsuarios - PreDestroy");
	}

	public Date getToday() {
		return today;
	}

	public void setToday(Date today) {
		this.today = today;
	}

	public List<User> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<User> usuarios) {
		this.usuarios = usuarios;
	}
	
	public String listadoUsuarios() {
		AdminService service;
		
		try {
			service = Factories.services.createAdminService();
			
			usuarios = service.findAllUsers();
			
			List<User> aux = new ArrayList<>();
			
			for(User user : usuarios) {
				if(!user.getIsAdmin()) {
					aux.add(user);
				}
			}
			
			usuarios = aux;
			
			return "exito";
			
		} catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	public String cambiarEstado(User usuario) {
		AdminService service;
		
		try {
			service = Factories.services.createAdminService();
			
			if(usuario.getStatus().equals(UserStatus.DISABLED))
				service.enableUser(usuario.getId());
			else
				service.disableUser(usuario.getId());
			
			listadoUsuarios();
			MessageToUser.writeGrowlMessageINFO("modificadoEstadoUsuario");
			return "exito";
			
		} catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	public String eliminarUsuario(User usuario) {
		AdminService service;
		
		try {
			service = Factories.services.createAdminService();
			service.deepDeleteUser(usuario.getId());
			
			listadoUsuarios();
			MessageToUser.writeGrowlMessageINFO("eliminadoUsuario");
			return "exito";
			
		} catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	public String reiniciarBaseDeDatos() {
		
		try {
			
			ReinicioBBDD.reiniciarBBDD(usuarios);
			
			MessageToUser.writeGrowlMessageINFO("baseDeDatosReiniciada");
			
			listadoUsuarios();
			return "exito";
			
		} catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	public List<Task> getTareasFiltradas() {
		return tareasFiltradas;
	}

	public void setTareasFiltradas(List<Task> tareasFiltradas) {
		this.tareasFiltradas = tareasFiltradas;
	}
}
