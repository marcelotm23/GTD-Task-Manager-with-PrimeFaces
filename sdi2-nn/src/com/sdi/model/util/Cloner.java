package com.sdi.model.util;

import com.sdi.model.Category;
import com.sdi.model.Task;
import com.sdi.model.User;


public class Cloner {

	public static User clone(User u) {
		User user=new User();
		user.setId( 		u.getId() );
		user.setEmail( 		u.getEmail() );
		user.setIsAdmin(	u.getIsAdmin() );
		user.setLogin( 		u.getLogin() );
		user.setPassword( 	u.getPassword() );
		user.setStatus( 	u.getStatus() );
		return user;
	}
	
	public static Task clone(Task t) {
		return new Task()
			.setCategoryId( t.getCategoryId() )
			.setComments( 	t.getComments() )
			.setCreated( 	t.getCreated() )
			.setFinished( 	t.getFinished() )
			.setId( 		t.getId() )
			.setPlanned( 	t.getPlanned() )
			.setTitle( 		t.getTitle() )
			.setUserId( 	t.getUserId() );
	}

	public static Category clone(Category c) {
		return new Category()
				.setName( 	c.getName() )
				.setUserId( c.getUserId() );
	}

}
