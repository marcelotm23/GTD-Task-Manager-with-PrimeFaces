package com.sdi.business;

public interface ServicesFactory {
	
	UserService createUserService();
	
	AdminService createAdminService();
	
	TaskService createTaskService();

}
