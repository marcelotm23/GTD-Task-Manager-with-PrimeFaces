package com.sdi.business.impl.task.command;

import java.util.List;

import com.sdi.business.exception.BusinessCheck;
import com.sdi.business.exception.BusinessException;
import com.sdi.business.impl.command.Command;
import com.sdi.model.Category;
import com.sdi.model.Task;
import com.sdi.model.User;
import com.sdi.model.types.UserStatus;
import com.sdi.model.util.Cloner;
import com.sdi.persistence.CategoryDao;
import com.sdi.persistence.Persistence;
import com.sdi.persistence.TaskDao;

public class DuplicateCategoryCommand implements Command<Long> {

	private Long origId;

	public DuplicateCategoryCommand(Long id) {
		this.origId = id;
	}

	@Override
	public Long execute() throws BusinessException {
		Long copyId = duplicateCategory( origId );
		duplicateTasks( origId, copyId );
		
		return copyId;
	}

	private Long duplicateCategory(Long id) throws BusinessException {
		CategoryDao cDao = Persistence.getCategoryDao();
		
		Category original = cDao.findById(id);
		BusinessCheck.isNotNull( original, "The category does not exist");
		checkUserNotDisabled( original );
		
		Category copy = Cloner.clone(original);
		copy.setName( copy.getName() + " - copy");
		return cDao.save( copy );
	}

	private void checkUserNotDisabled(Category c) throws BusinessException {
		User u = Persistence.getUserDao().findById( c.getUserId() );
		BusinessCheck.isTrue( u.getStatus().equals( UserStatus.ENABLED ),
				"User disables, category cannot be duplicated.");
	}

	private void duplicateTasks(Long catId, Long copyId) {
		TaskDao tDao = Persistence.getTaskDao();

		List<Task> tasks = tDao.findTasksByCategoryId( catId );
		for(Task t: tasks) {
			Task copy = Cloner.clone(t);
			copy.setCategoryId( copyId );
			tDao.save( copy );
		}
	}

}
