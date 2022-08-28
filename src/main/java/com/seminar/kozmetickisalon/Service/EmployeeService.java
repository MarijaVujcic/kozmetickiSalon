package com.seminar.kozmetickisalon.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seminar.kozmetickisalon.Model.Employee;
import com.seminar.kozmetickisalon.Repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    OfferService offerService;

    
    public Object findAll() {
        return employeeRepository.findAll();
    }


    public void deletEmployee(Integer valueOf) {
        employeeRepository.delete(employeeRepository.findById(valueOf).get());
    }


    public Employee findById(Integer valueOf) {
        return employeeRepository.findById(valueOf).get();
    }


    public void createEmployee(Employee user, Integer valueOf) {
        Employee employee = new Employee();
        employee.setName(user.getName());
        employee.getOffers().add(offerService.findById(valueOf));
        employeeRepository.save(employee);
    }
    
}
