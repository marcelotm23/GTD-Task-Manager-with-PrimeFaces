package com.sdi.business.impl;


import com.sdi.business.AdminService;
import com.sdi.business.AlumnosService;
import com.sdi.business.ServicesFactory;
import com.sdi.business.TaskService;
import com.sdi.business.UserService;

public class SimpleServicesFactory implements ServicesFactory {

	

	@Override
	public UserService createUserService() {
		return null;
//		return new SimpleUserService();
	}

	@Override
	public AdminService createAdminService() {
		return null;
//		return new SimpleAdminService();
	}

	@Override
	public TaskService createTaskService() {
		return null;
//		return new SimpleTaskService();
	}

}
