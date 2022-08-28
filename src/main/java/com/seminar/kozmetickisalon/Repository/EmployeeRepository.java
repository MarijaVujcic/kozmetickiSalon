package com.seminar.kozmetickisalon.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seminar.kozmetickisalon.Model.Employee;

public interface EmployeeRepository  extends JpaRepository<Employee, Integer>{
    
    
}
