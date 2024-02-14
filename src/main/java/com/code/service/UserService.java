package com.code.service;

import com.code.entities.User;

public interface UserService {
	
    public User saveUser(User user);
    
    public void removeMessage();
}
