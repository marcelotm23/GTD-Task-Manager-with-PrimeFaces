package com.sdi.business;

import com.sdi.business.exception.BusinessException;
import com.sdi.model.User;

public interface UserService {

	public Long registerUser(User user) throws BusinessException;

	public void updateUserDetails(User user) throws BusinessException;

	public User findLoggableUser(String login, String password)
			throws BusinessException;

}
