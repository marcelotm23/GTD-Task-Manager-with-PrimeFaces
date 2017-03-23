package com.sdi.model.util;

import com.sdi.model.Category;
import com.sdi.model.Task;
import com.sdi.model.User;

public class Cloner {

	public static User clone(User u) {
		User user = new User();
		user.setId(u.getId());
		user.setEmail(u.getEmail());
		user.setIsAdmin(u.getIsAdmin());
		user.setLogin(u.getLogin());
		user.setPassword(u.getPassword());
		user.setStatus(u.getStatus());
		return user;
	}

	public static Task clone(Task t) {
		Task task = new Task();
		task.setCategoryId(t.getCategoryId());
		task.setComments(t.getComments());
		task.setCreated(t.getCreated());
		task.setFinished(t.getFinished());
		task.setId(t.getId());
		task.setPlanned(t.getPlanned());
		task.setTitle(t.getTitle());
		task.setUserId(t.getUserId());
		return task;
	}

	public static Category clone(Category c) {
		Category category = new Category();
		category.setName(c.getName());
		category.setUserId(c.getUserId());
		return category;
	}

}
