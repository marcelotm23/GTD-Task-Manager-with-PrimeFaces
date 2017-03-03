package com.sdi.model.util;

import com.sdi.model.Category;
import com.sdi.model.Task;
import com.sdi.model.User;


public class Cloner {

	public static User clone(User u) {
		return new User()
			.setId( 		u.getId() )
			.setEmail( 		u.getEmail() )
			.setIsAdmin(	u.getIsAdmin() )
			.setLogin( 		u.getLogin() )
			.setPassword( 	u.getPassword() )
			.setStatus( 	u.getStatus() );
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
