package com.company.employeemanagement.service;

import java.util.List;

import org.springframework.data.domain.Sort.Direction;

import com.company.employeemanagement.entity.Employee;

public interface EmployeeService {

	public Employee save(Employee employee);

	public List<Employee> findAll();

	public Employee findById(long id);

	public Employee updateEmployee(Employee employee);

	public List<Employee> findByFirstName(String firstName);

	public String deleteEmployeeById(long id);

	public List<Employee> customSortEmployeeByName(Direction direction);

}
