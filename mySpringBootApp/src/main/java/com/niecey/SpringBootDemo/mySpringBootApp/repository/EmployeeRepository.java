package com.niecey.SpringBootDemo.mySpringBootApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.niecey.SpringBootDemo.mySpringBootApp.model.Employee;

@Repository

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
