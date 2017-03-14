package com.sdi.business.impl;


import com.sdi.business.AdminService;
import com.sdi.business.ServicesFactory;
import com.sdi.business.TaskService;
import com.sdi.business.UserService;

public class SimpleServicesFactory implements ServicesFactory {

	

	@Override
	public UserService createUserService() {
		return new SimpleUserService();
	}

	@Override
	public AdminService createAdminService() {
		return new SimpleAdminService();
	}

	@Override
	public TaskService createTaskService() {
		return new SimpleTaskService();
	}

}
