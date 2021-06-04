package com.bank.user.utility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseObject {

	private Object data;
	private boolean success;
	private String message;
	private String userType;
}
