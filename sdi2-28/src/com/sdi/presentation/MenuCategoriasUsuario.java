package com.sdi.presentation;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import com.sdi.business.Services;
import com.sdi.business.TaskService;
import com.sdi.model.Category;
import com.sdi.model.User;

@ManagedBean(name = "menuCategorias")
public class MenuCategoriasUsuario {

	private MenuModel model;
	private List<Category> categorias;
	private BeanUsuario usuario;

	@PostConstruct
	public void init() {
		model = new DefaultMenuModel();
		usuario = (BeanUsuario) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap()
				.get(new String("usuario"));

		// // si no existe lo creamos e inicializamos
		if (usuario == null) {
			System.out.println("BeanUsuarios - No existia");
			usuario = new BeanUsuario();
			usuario.setUsuario((User) FacesContext.getCurrentInstance()
					.getExternalContext().getSessionMap()
					.get(new String("LOGGEDIN_USER")));

			
		}
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put("usuario", usuario);
		cargarCategoriasUsuario();
		// Second submenu
		DefaultSubMenu secondSubmenu = new DefaultSubMenu("");
		DefaultMenuItem item = new DefaultMenuItem();

		if (categorias != null && !categorias.isEmpty()) {
			for (Category c : categorias) {
				item = new DefaultMenuItem(c.getName());
				item.setIcon("ui-icon-tag");
				item.setCommand("#{controller.cargarTareasCategoria}");
				item.setParam("catSelecId", c.getId());
				item.setUpdate("tablalistado");
				secondSubmenu.addElement(item);
			}
		}

		model.addElement(secondSubmenu);
	}

	public MenuModel getModel() {
		return model;
	}

	public String cargarCategoriasUsuario() {
		TaskService taskService;
		try {
			taskService = Services.getTaskService();
			categorias = taskService.findCategoriesByUserId(usuario.getId());
			return "exito";

		} catch (Exception e) {
			e.printStackTrace();
			return "error"; // Nos vamos a la vista de error.
		}
	}

	public void addMessage(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}