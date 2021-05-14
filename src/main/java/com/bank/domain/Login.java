package com.bank.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Login {
	@NotBlank(message = "{login.phoneno.blank}")
	@Pattern(regexp = "[0-9]{10}", message = "{login.phoneno.invalid}")
	private String phoneNo;
	
	@NotBlank(message = "{login.password.blank}")
	private String password;
	
	@NotBlank(message = "{login.userrole.blank}")
	@Pattern(regexp = "(ADMIN|EMPLOYEE|CUSTOMER)", message = "{login.userrole.invalid}")
	private String userRole;
}
