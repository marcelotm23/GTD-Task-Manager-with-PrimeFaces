package com.sdi.business.impl;

import java.util.List;

import com.sdi.business.TaskService;
import com.sdi.business.exception.BusinessException;
import com.sdi.business.impl.task.TaskServiceImpl;
import com.sdi.model.Category;
import com.sdi.model.Task;

public class SimpleTaskService implements TaskService {

	@Override
	public Long createCategory(Category category) throws BusinessException {
		return new TaskServiceImpl().createCategory(category);
	}

	@Override
	public Long duplicateCategory(Long id) throws BusinessException {
		return new TaskServiceImpl().duplicateCategory(id);
	}

	@Override
	public void updateCategory(Category category) throws BusinessException {
		new TaskServiceImpl().updateCategory(category);

	}

	@Override
	public void deleteCategory(Long id) throws BusinessException {
		new TaskServiceImpl().deleteCategory(id);

	}

	@Override
	public Category findCategoryById(Long id) throws BusinessException {
		return new TaskServiceImpl().findCategoryById(id);
	}
	
	@Override
	public Category findCategoryByUserIdAndName(Long id, String name)
			throws BusinessException {
		return new TaskServiceImpl().findCategoryByUserIdAndName(id, name);
	}

	@Override
	public List<Category> findCategoriesByUserId(Long id)
			throws BusinessException {
		return new TaskServiceImpl().findCategoriesByUserId(id);
	}

	@Override
	public Long createTask(Task task) throws BusinessException {
		return new TaskServiceImpl().createTask(task);
	}

	@Override
	public void deleteTask(Long id) throws BusinessException {
		new TaskServiceImpl().deleteTask(id);
	}

	@Override
	public void markTaskAsFinished(Long id) throws BusinessException {
		new TaskServiceImpl().markTaskAsFinished(id);
	}

	@Override
	public void updateTask(Task task) throws BusinessException {
		new TaskServiceImpl().updateTask(task);
	}

	@Override
	public Task findTaskById(Long id) throws BusinessException {
		return new TaskServiceImpl().findTaskById(id);
	}

	@Override
	public List<Task> findInboxTasksByUserId(Long id) throws BusinessException {
		return new TaskServiceImpl().findInboxTasksByUserId(id);
	}

	@Override
	public List<Task> findWeekTasksByUserId(Long id) throws BusinessException {
		return new TaskServiceImpl().findWeekTasksByUserId(id);
	}

	@Override
	public List<Task> findTodayTasksByUserId(Long id) throws BusinessException {
		return new TaskServiceImpl().findTodayTasksByUserId(id);
	}

	@Override
	public List<Task> findTasksByCategoryId(Long catId)
			throws BusinessException {
		return new TaskServiceImpl().findTasksByCategoryId(catId);
	}

	@Override
	public List<Task> findFinishedTasksByCategoryId(Long catId)
			throws BusinessException {
		return new TaskServiceImpl().findFinishedTasksByCategoryId(catId);
	}

	@Override
	public List<Task> findFinishedInboxTasksByUserId(Long userId)
			throws BusinessException {
		return new TaskServiceImpl().findFinishedInboxTasksByUserId(userId);
	}

}
