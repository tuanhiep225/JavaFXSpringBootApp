package app.service;

import app.bean.User;
import app.generic.GenericService;

public interface UserService extends GenericService<User> {

	boolean authenticate(String email, String password);
	
	User findByEmail(String email);
	
}
