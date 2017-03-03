package com.mert.service;

import com.mert.model.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
}
