package com.bank.domain;

import lombok.Data;

@Data
public abstract class User {

	protected String phoneNo;
	protected String password;
}
