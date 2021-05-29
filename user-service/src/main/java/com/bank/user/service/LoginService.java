package com.bank.user.service;

import com.bank.user.domain.Login;
import com.bank.user.domain.User;
import com.bank.user.utility.UserNotFoundException;

public interface LoginService {

	public User loginUser(Login credentials) throws UserNotFoundException;
}
