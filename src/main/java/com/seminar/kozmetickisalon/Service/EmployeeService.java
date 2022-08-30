package com.seminar.kozmetickisalon.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seminar.kozmetickisalon.Model.Employee;
import com.seminar.kozmetickisalon.Model.Offer;
import com.seminar.kozmetickisalon.Repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    OfferService offerService;

    
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }


    public void deletEmployee(Integer valueOf) {
        employeeRepository.delete(employeeRepository.findById(valueOf).get());
    }


    public Employee findById(Integer valueOf) {
        return employeeRepository.findById(valueOf).get();
    }


    public void createEmployee(Employee user) {
        Employee employee = new Employee();
        employee.setName(user.getName());
        employeeRepository.save(employee);
    }


    public void update(Employee updEmployee) {
        employeeRepository.save(updEmployee);
    }



    
}
