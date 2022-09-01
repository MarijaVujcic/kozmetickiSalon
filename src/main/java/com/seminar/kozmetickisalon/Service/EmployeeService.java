package com.seminar.kozmetickisalon.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seminar.kozmetickisalon.Model.Employee;
import com.seminar.kozmetickisalon.Model.Offer;
import com.seminar.kozmetickisalon.Model.Reservations;
import com.seminar.kozmetickisalon.Repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    OfferService offerService;

    @Autowired
    ReservationService reservationService;

    
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }


    public void deleteEmployee(Integer valueOf) {
        List<Reservations> res = this.findById(valueOf).getReservations().stream().toList();
        this.findById(valueOf).setReservations(null);
        for(int i = 0; i< res.size();i++){
            Reservations r =reservationService.reservationRepository.findById(res.get(i).getReservation_id()).get();
            r.setEmployee(null);
            reservationService.reservationRepository.save(r);
            reservationService.reservationRepository.flush();
        }
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
