package com.sdi.business.impl.user.command;

import com.sdi.business.exception.BusinessException;
import com.sdi.business.impl.command.Command;
import com.sdi.business.impl.util.UserCheck;
import com.sdi.model.User;
import com.sdi.persistence.Persistence;

public class RegisterUserCommand implements Command<Long> {

	private User user;

	public RegisterUserCommand(User user) {
		this.user = user;
	}

	@Override
	public Long execute() throws BusinessException {
		UserCheck.isNotAdmin( user );
		UserCheck.isValidEmailSyntax( user ); 
		UserCheck.minLoginLength( user );
		UserCheck.minPasswordLength( user );
		UserCheck.notRepeatedLogin( user );
		
		return Persistence.getUserDao().save( user );
	}

}
