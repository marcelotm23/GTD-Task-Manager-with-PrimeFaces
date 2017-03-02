package com.sdi.business.impl.admin.command;

import com.sdi.business.exception.BusinessCheck;
import com.sdi.business.exception.BusinessException;
import com.sdi.business.impl.command.Command;
import com.sdi.model.User;
import com.sdi.model.types.UserStatus;
import com.sdi.persistence.Persistence;
import com.sdi.persistence.UserDao;

public class DisableUserCommand implements Command<Void> {

	private Long id;

	public DisableUserCommand(Long id) {
		this.id = id;
	}

	@Override
	public Void execute() throws BusinessException {
		UserDao uDao = Persistence.getUserDao();
		
		User user = uDao.findById(id);
		BusinessCheck.isNotNull( user, "User does not exist" );
		
		user.setStatus( UserStatus.DISABLED );
		uDao.update( user );

		return null;
	}

}
