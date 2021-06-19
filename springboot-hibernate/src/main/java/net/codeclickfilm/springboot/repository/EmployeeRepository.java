package net.codeclickfilm.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.codeclickfilm.springboot.model.Employee;
//@Repository bcoz Spring Data JPA handles
public interface EmployeeRepository extends JpaRepository<Employee, Long> { 

}
