package com.sdi.business.impl.task.command;

import com.sdi.business.exception.BusinessCheck;
import com.sdi.business.exception.BusinessException;
import com.sdi.business.impl.command.Command;
import com.sdi.business.impl.util.TaskCheck;
import com.sdi.model.Task;
import com.sdi.persistence.Persistence;
import com.sdi.persistence.TaskDao;

public class UpdateTaskCommand implements Command<Void> {

	private Task task;

	public UpdateTaskCommand(Task task) {
		this.task = task;
	}

	@Override
	public Void execute() throws BusinessException {
		TaskCheck.titleIsNotNull(task);
		TaskCheck.titleIsNotEmpty(task);
		if (task.getCategoryId() != null) {
			TaskCheck.categoryExists(task);
		}

		TaskDao tDao = Persistence.getTaskDao();

		Task previous = tDao.findById(task.getId());
		checktaskAlreadyExist(previous);
		checkUserNotChanged(previous);

		task.setCreated(previous.getCreated()); // change ignored
		tDao.update(task);
		return null;
	}

	private void checktaskAlreadyExist(Task previous) throws BusinessException {
		BusinessCheck.isNotNull(previous, "The task does not exist");
	}

	private void checkUserNotChanged(Task previous) throws BusinessException {
		BusinessCheck.isTrue(task.getUserId().equals(previous.getUserId()),
				"A task cannot be changed to other user");
	}

}
