package com.bank.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.bank.domain.Employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "employee")
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEntity {

	private String id;

	@Column(nullable = false, length = 50)
	private String name;

	@Column(nullable = false, length = 50)
	private LocalDate dateOfBirth;

	@Column(nullable = false, length = 50)
	private String phoneNo;

	@Column(nullable = false, length = 50)
	private String emailId;

	@Column(nullable = false, length = 50)
	private String address;

	@Column(nullable = false, length = 50)
	private Character gender;

	@Column(nullable = false, length = 50)
	private LocalDate joiningDate;

	@Column(nullable = false, length = 50)
	private AccountEntity account;

	@Column(nullable = false, length = 50)
	private DepartmentEntity department;

	/**
	 * Method to prepare Employee DTO from Employee Entity
	 * 
	 * @param employeeEntity
	 * @return Employee DTO
	 */
	public static Employee prepareEmployeeDTO(EmployeeEntity employeeEntity) {
		Employee employee = new Employee(employeeEntity.getId(), employeeEntity.getName(),
				employeeEntity.getDateOfBirth(), employeeEntity.getPhoneNo(), employeeEntity.getEmailId(),
				employeeEntity.getAddress(), employeeEntity.getGender(), employeeEntity.getJoiningDate(),
				AccountEntity.prepareDto(employeeEntity.getAccount()),
				DepartmentEntity.prepareDTO(employeeEntity.getDepartment()));
		return employee;
	}

	/**
	 * Method to prepare List of Employee DTO from List of Employee Entity
	 * 
	 * @param employeeEntities
	 * @return List of Employee DTO
	 */
	public static List<Employee> prepareEmployeeDTOList(List<EmployeeEntity> employeeEntities) {
		List<Employee> employees = new ArrayList<>();
		for(EmployeeEntity employeeEntity: employeeEntities) {
			employees.add(prepareEmployeeDTO(employeeEntity));
		}
		return employees;
	}
}
