package com.sdi.business.impl;

import java.util.List;

import com.sdi.business.AdminService;
import com.sdi.business.exception.BusinessException;
import com.sdi.business.impl.admin.AdminServiceImpl;
import com.sdi.model.User;

public class SimpleAdminService implements AdminService {

	@Override
	public void deepDeleteUser(Long id) throws BusinessException {
		new AdminServiceImpl().deepDeleteUser(id);

	}

	@Override
	public void disableUser(Long id) throws BusinessException {
		new AdminServiceImpl().disableUser(id);

	}

	@Override
	public void enableUser(Long id) throws BusinessException {
		new AdminServiceImpl().enableUser(id);

	}

	@Override
	public List<User> findAllUsers() throws BusinessException {
		return new AdminServiceImpl().findAllUsers();
	}

	@Override
	public User findUserById(Long id) throws BusinessException {
		return new AdminServiceImpl().findUserById(id);
	}

}
