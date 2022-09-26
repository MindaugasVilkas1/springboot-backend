package com.springboot.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.springboot.springboot.model.Employee;

public interface EmployeeRepository extends JpaRepository <Employee, Long> {

}
