package com.seminar.kozmetickisalon.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.seminar.kozmetickisalon.Model.Employee;
import com.seminar.kozmetickisalon.Model.Offer;
import com.seminar.kozmetickisalon.Service.EmployeeService;
import com.seminar.kozmetickisalon.Service.OfferService;
@Controller
@RequestMapping("/employee")
public class  EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    OfferService offerService;

    @GetMapping("/config")
    ModelAndView showUserConfiguration(){
        ModelAndView mv = new ModelAndView("employeeConfiguration");
        mv.addObject("employees", employeeService.findAll());
        mv.addObject("employeeToAdd", new Employee());
        mv.addObject("offers", offerService.findAll());

        return mv;
    }

    @GetMapping("/deleteEmployee/{id}")
    String deleteEmployeeString(@PathVariable String id){
        employeeService.deleteEmployee(Integer.valueOf(id));
        return "redirect:/employee/config";
    }


    @PostMapping("/addEmployee")
    String createNewEmployee(@ModelAttribute Employee user){
        employeeService.createEmployee(user);
        return "redirect:/employee/config";
    }

    @PostMapping("/updateEmployee/")
    ModelAndView updateUserShow( String id){
        ModelAndView mv = new ModelAndView("employeeConfiguration");
        Employee updUser = employeeService.findById(Integer.valueOf(id));
        mv.addObject("employees", employeeService.findAll());
        mv.addObject("employeeToAdd", new Employee());
        mv.addObject("offers", offerService.findAll());
        mv.addObject("updateEmployee", updUser);
        return mv;
    } 

    @PostMapping("/updateEmployee")
    String updateEmployeeSave(@ModelAttribute Employee user){
        Employee updEmployee = employeeService.findById(user.getEmployee_id());
        updEmployee.setName(user.getName());
        
        employeeService.update(updEmployee);
        return "redirect:/employee/config";
    }
   
    
}
