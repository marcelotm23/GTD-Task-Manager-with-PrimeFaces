package com.sdi.presentation.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import alb.util.date.DateUtil;

import com.sdi.business.AdminService;
import com.sdi.business.TaskService;
import com.sdi.business.UserService;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Category;
import com.sdi.model.Task;
import com.sdi.model.User;
import com.sdi.model.types.UserStatus;

public class ReinicioBBDD {

	public static void reiniciarBBDD(List<User> usuarios) {
		AdminService adminService;
		UserService userService;
		TaskService taskService;

		try {
			// Eliminar a los usuarios, sus tareas y sus categorias
			adminService = Factories.services.createAdminService();
			userService = Factories.services.createUserService();
			taskService = Factories.services.createTaskService();

			usuarios = borrarUsuariosYCrearNuevos(adminService, userService,
					usuarios);

			crearCategorias(taskService, usuarios);

			crearTareas(taskService, usuarios);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	private static void crearTareas(TaskService taskService, List<User> usuarios) {

		for (User u : usuarios) {

			int contadorTarea = 1;

			for (int i = 1; i < 11; i++) {
				Task auxTask = new Task();
				auxTask.setTitle("tarea" + contadorTarea);
				auxTask.setCreated(new Date());
				auxTask.setUserId(u.getId());
				Date date = DateUtil.today();
				auxTask.setPlanned(date);
				taskService.createTask(auxTask);
				contadorTarea++;
			}
			for (int i = 1; i < 11; i++) {
				Task auxTask = new Task();
				auxTask.setTitle("tarea" + contadorTarea);
				auxTask.setCreated(new Date());
				auxTask.setUserId(u.getId());
				Date date = DateUtil.today();
				date.setDate(date.getDate() + 6);
				auxTask.setPlanned(date);
				taskService.createTask(auxTask);
				contadorTarea++;
			}
			crearTareasConCategoria(taskService, u, contadorTarea);
		}

	}

	@SuppressWarnings("deprecation")
	private static void crearTareasConCategoria(TaskService taskService,
			User usuario, int contadorTarea) {

		int contador = 1;

		for (int i = 0; i < 6; i++) {
			Task auxTask = new Task();
			auxTask.setTitle("tarea" + contadorTarea);
			auxTask.setCreated(new Date());
			auxTask.setUserId(usuario.getId());
			Date date = DateUtil.today();
			date.setDate(date.getDate() - 6);
			auxTask.setPlanned(date);
			Category category = taskService.findCategoryByUserIdAndName(
					usuario.getId(), "categoria" + contador);
			auxTask.setCategoryId(category.getId());
			taskService.createTask(auxTask);
			if (i == 2)
				contador++;
			contadorTarea++;
		}

		for (int i = 0; i < 4; i++) {
			Task auxTask = new Task();
			auxTask.setTitle("tarea" + contadorTarea);
			auxTask.setCreated(new Date());
			auxTask.setUserId(usuario.getId());
			Date date = DateUtil.today();
			date.setDate(date.getDate() - 6);
			auxTask.setPlanned(date);
			Category category = taskService.findCategoryByUserIdAndName(
					usuario.getId(), "categoria3");
			auxTask.setCategoryId(category.getId());
			taskService.createTask(auxTask);
			contadorTarea++;
		}

	}

	private static List<User> borrarUsuariosYCrearNuevos(
			AdminService adminService, UserService userService,
			List<User> usuarios) {

		List<User> listaUsuariosNuevos = new ArrayList<User>();

		// Borrar usuarios BBDD
		for (User user : usuarios) {
			adminService.deepDeleteUser(user.getId());
		}

		// Crear usuarios
		for (int i = 1; i < 4; i++) {
			User aux = new User();
			aux.setLogin("user" + i);
			aux.setEmail("user" + i + "@user.com");
			aux.setStatus(UserStatus.ENABLED);
			aux.setPassword("user" + i);
			aux.setIsAdmin(false);
			userService.registerUser(aux);
		}

		List<User> aux = adminService.findAllUsers();

		for (User u : aux) {
			if (!u.getIsAdmin()) {
				listaUsuariosNuevos.add(u);
			}
		}

		return listaUsuariosNuevos;
	}

	private static void crearCategorias(TaskService taskService,
			List<User> usuarios) {

		for (User user : usuarios) {
			for (int i = 1; i < 4; i++) {
				Category aux = new Category();
				aux.setUserId(user.getId());
				aux.setName("categoria" + i);
				taskService.createCategory(aux);
			}
		}

	}
}
