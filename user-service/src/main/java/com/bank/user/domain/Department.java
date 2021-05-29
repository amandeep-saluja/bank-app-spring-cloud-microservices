package com.bank.user.domain;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.bank.user.entity.DepartmentEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {

	private long id;
	
	@NotBlank(message = "Department name cannot be empty")
	private String name;
	
	@NotBlank(message = "Department type cannot be empty")
	private DepartmentType type;
	
	/**
	 * Method to prepare {@link DepartmentEntity} object from {@link Department} DTO object.
	 * @param department
	 * @return {@link DepartmentEntity}
	 */
	public static DepartmentEntity prepareEntity(Department department) {
		DepartmentEntity departmentEntity = new DepartmentEntity(department.getId(), department.getName(), department.getType());
		return departmentEntity;
	}
	
	/**
	 * Method to prepare List of DepartmentEntity from List of Department DTOs. 
	 * @param departments
	 * @return List of DepartmentEntity
	 */
	public static List<DepartmentEntity> prepareEntities(List<Department> departments) {
		List<DepartmentEntity> departmentEntities = new ArrayList<>();
		for(Department department: departments) {
			departmentEntities.add(prepareEntity(department));
		}
		return departmentEntities;
	}
}
