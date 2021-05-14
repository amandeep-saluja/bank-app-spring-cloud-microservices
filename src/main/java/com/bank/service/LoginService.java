package com.bank.service;

import com.bank.domain.Login;
import com.bank.domain.User;
import com.bank.utility.UserNotFoundException;

public interface LoginService {

	public User loginUser(Login credentials) throws UserNotFoundException;
}
