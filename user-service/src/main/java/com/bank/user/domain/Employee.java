package com.bank.user.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;

import com.bank.user.entity.EmployeeEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Employee extends User {

	private Integer id;

	@NotBlank(message = "Name cannnot be empty")
	private String name;

	@NotBlank(message = "Password cannnot be empty")
	private String password;

	@Past(message = "Date of birth should be past")
	@NotBlank(message = "Date of birth cannnot be empty")
	private LocalDate dateOfBirth;

	@Pattern(regexp = "[0-9]{10}", message = "Phone number should be of 10 digits")
	@NotBlank(message = "Phone no cannnot be empty")
	private String phoneNo;

	@NotBlank(message = "Eamil id cannnot be empty")
	@Email(message = "Enter valid email id")
	private String emailId;

	@NotBlank(message = "Address cannnot be empty")
	private String address;

	@NotBlank(message = "Gender cannnot be empty")
	@Pattern(regexp = "(M|F){1}", message = "Gender value can only be M or F")
	private Character gender;

	@PastOrPresent(message = "Joining date can only be of past or present")
	@NotBlank(message = "Joining Date cannnot be empty")
	private LocalDate joiningDate;

	@NotBlank(message = "Account cannnot be empty")
	private Account account;

	@NotBlank(message = "Department cannnot be empty")
	private Department department;

	/**
	 * Method to prepare Employee Entity from Employee DTO
	 * 
	 * @param employee
	 * @return Employee Entity
	 */
	public static EmployeeEntity prepareEntity(Employee employee) {
		EmployeeEntity employeeEntity = new EmployeeEntity(employee.getId(), employee.getName(), employee.getPassword(),
				employee.getDateOfBirth(), employee.getPhoneNo(), employee.getEmailId(), employee.getAddress(),
				employee.getGender(), employee.getJoiningDate(), employee.getAccount().getId(),
				Department.prepareEntity(employee.getDepartment()));
		return employeeEntity;
	}

	/**
	 * Method to prepare List of EmployeeEntity from List of Employee DTOs
	 * 
	 * @param employees
	 * @return List of Employee Entity
	 */
	public static List<EmployeeEntity> prepareEntityList(List<Employee> employees) {
		List<EmployeeEntity> employeeEntities = new ArrayList<>();
		for (Employee employee : employees) {
			employeeEntities.add(prepareEntity(employee));
		}
		return employeeEntities;
	}
}
