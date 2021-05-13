package com.bank.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bank.domain.Department;
import com.bank.domain.DepartmentType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "department")
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentEntity {

	@Id
	private String id;
	
	@Column(nullable = false, length = 50)
	private String name;
	
	@Column(nullable = false, length = 50)
	private DepartmentType type;
	
	/**
	 * Method to prepare {@link Department} from {@link DepartmentEntity}
	 * @param departmentEntity
	 * @return Department
	 */
	public static Department prepareDTO(DepartmentEntity departmentEntity) {
		Department department = new Department(departmentEntity.getId(), departmentEntity.getName(), departmentEntity.getType());
		return department;
	}
	
	/**
	 * Method to prepare List of Department DTOs from List of Department Entity 
	 * @param departmentEntities
	 * @return List of Department 
	 */
	public static List<Department> prepareDTOList(List<DepartmentEntity> departmentEntities) {
		List<Department> departments = new ArrayList<>();
		for(DepartmentEntity departmentEntity: departmentEntities) {
			departments.add(prepareDTO(departmentEntity));
		}
		return departments;
	}
}
