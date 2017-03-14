package com.sdi.business.impl;

import com.sdi.business.UserService;
import com.sdi.business.exception.BusinessException;
import com.sdi.business.impl.user.UserServiceImpl;
import com.sdi.model.User;

public class SimpleUserService implements UserService {

	@Override
	public Long registerUser(User user) throws BusinessException {
		return new UserServiceImpl().registerUser(user);
	}

	@Override
	public void updateUserDetails(User user) throws BusinessException {
		new UserServiceImpl().updateUserDetails(user);
	}

	@Override
	public User findLoggableUser(String login, String password)
			throws BusinessException {
		return new UserServiceImpl().findLoggableUser(login, password);
	}

}
