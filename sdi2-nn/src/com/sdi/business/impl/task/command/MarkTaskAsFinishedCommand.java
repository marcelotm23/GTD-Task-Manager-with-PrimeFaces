package com.sdi.business.impl.task.command;

import alb.util.date.DateUtil;
import com.sdi.business.exception.BusinessCheck;
import com.sdi.business.exception.BusinessException;
import com.sdi.business.impl.command.Command;
import com.sdi.model.Task;
import com.sdi.persistence.Persistence;
import com.sdi.persistence.TaskDao;

public class MarkTaskAsFinishedCommand implements Command<Void> {

	private Long id;

	public MarkTaskAsFinishedCommand(Long id) {
		this.id = id;
	}

	@Override
	public Void execute() throws BusinessException {
		TaskDao tDao = Persistence.getTaskDao();
		
		Task t = tDao.findById(id);
		BusinessCheck.isNotNull(t, "The task does not exist");
		
		t.setFinished( DateUtil.today() );
		tDao.update( t );
		return null;
	}

}
