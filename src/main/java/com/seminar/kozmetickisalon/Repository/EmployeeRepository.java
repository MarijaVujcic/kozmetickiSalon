package com.seminar.kozmetickisalon.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.seminar.kozmetickisalon.Model.Employee;

public interface EmployeeRepository  extends JpaRepository<Employee, Integer>{
    
    @Query(value = "select t from employee t join offer u where u.offer_id = :id" , nativeQuery = true)
    List<Employee> findAllByOffer(@Param("id")Integer id);
}
