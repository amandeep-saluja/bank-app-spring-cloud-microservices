package com.bank.user.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.bank.user.domain.Account;
import com.bank.user.domain.Employee;
import com.bank.user.domain.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "employee")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EmployeeEntity extends User {

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false, length = 50)
	private String name;

	@Column(nullable = false, length = 50)
	private String password;

	@Column(nullable = false, name = "date_of_birth")
	private LocalDate dateOfBirth;

	@Column(nullable = false, length = 10, name = "phone_no")
	private String phoneNo;

	@Column(nullable = false, length = 50, name = "email_id")
	private String emailId;

	@Column(nullable = false, length = 50)
	private String address;

	@Column(nullable = false)
	private Character gender;

	@Column(nullable = false, name = "joining_date")
	private LocalDate joiningDate;

	@Column(nullable = false, name = "account_id")
	private long accountId;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "department_id")
	private DepartmentEntity department;

	/**
	 * Method to prepare Employee DTO from Employee Entity
	 * 
	 * @param employeeEntity
	 * @return Employee DTO
	 */
	public static Employee prepareEmployeeDTO(EmployeeEntity employeeEntity) {
		Account account = new Account();
		account.setId(employeeEntity.getAccountId());
		Employee employee = new Employee(employeeEntity.getId(), employeeEntity.getName(), employeeEntity.getPassword(),
				employeeEntity.getDateOfBirth(), employeeEntity.getPhoneNo(), employeeEntity.getEmailId(),
				employeeEntity.getAddress(), employeeEntity.getGender(), employeeEntity.getJoiningDate(),
				account, DepartmentEntity.prepareDTO(employeeEntity.getDepartment()));
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
		for (EmployeeEntity employeeEntity : employeeEntities) {
			employees.add(prepareEmployeeDTO(employeeEntity));
		}
		return employees;
	}
}
