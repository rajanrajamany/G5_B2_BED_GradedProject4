package com.company.employeemanagement.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.company.employeemanagement.entity.Employee;
import com.company.employeemanagement.entity.Role;
import com.company.employeemanagement.entity.User;
import com.company.employeemanagement.repository.EmployeeRepository;
import com.company.employeemanagement.repository.UserRepository;

@Component
public class BootstrapAppData {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@EventListener(ApplicationReadyEvent.class)
	public void insertEmployeeData(ApplicationReadyEvent event) {

		// Creating Dummy data in database
		Employee employee = new Employee();
		employee.setFirstName("Piyush");
		employee.setLastName("Saxena");

		employee.setEmail(employee.getFirstName() + "@" + employee.getLastName() + ".com");

		Employee employee1 = new Employee();
		employee1.setFirstName("Ram");
		employee1.setLastName("Sharma");

		employee1.setEmail(employee1.getFirstName() + "@" + employee1.getLastName() + ".com");

		this.employeeRepository.save(employee);
		this.employeeRepository.save(employee1);

	}

	@EventListener(ApplicationReadyEvent.class)
	public void insertRolesData(ApplicationReadyEvent event) {

		// Creating application ready users in database
		User piyush = new User();
		piyush.setUsername("piyush");
		piyush.setPassword(this.passwordEncoder.encode("piyush"));

		User ranjan = new User();
		ranjan.setUsername("ranjan");
		ranjan.setPassword(this.passwordEncoder.encode("ranjan"));

		Role userRole = new Role();
		userRole.setName("USER");

		Role adminRole = new Role();
		adminRole.setName("ADMIN");

		piyush.addRole(adminRole);
		ranjan.addRole(userRole);

		this.userRepository.save(piyush);
		this.userRepository.save(ranjan);

	}

}
