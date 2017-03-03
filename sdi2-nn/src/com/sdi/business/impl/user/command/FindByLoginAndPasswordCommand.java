package com.sdi.business.impl.user.command;

import com.sdi.business.exception.BusinessException;
import com.sdi.business.impl.command.Command;
import com.sdi.model.User;

public class FindByLoginAndPasswordCommand implements Command<User> {

	@Override
	public User execute() throws BusinessException {
		
		return null;
	}

}
