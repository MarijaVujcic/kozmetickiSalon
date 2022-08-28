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
    String deleteEmployee(@PathVariable String id){
        employeeService.deletEmployee(Integer.valueOf(id));
        return "redirect:/employee/config";
    }

    @PostMapping("/addEmployee")
    String createNewEmployee(@ModelAttribute Employee user, String selectedOffer){
        employeeService.createEmployee(user, Integer.valueOf(selectedOffer));
        return "redirect:/employee/config";
    }

    @GetMapping("/updateEmployee/{id}")
    ModelAndView updateUserShow(@PathVariable String id){
        ModelAndView mv = new ModelAndView("employeeConfiguration");
        Employee updUser = employeeService.findById(Integer.valueOf(id));
        List<Offer> offersOfUser = updUser.getOffers().stream().toList();
        List<Offer> offrN = offerService.findAll();
        offrN.removeAll(offersOfUser);
        mv.addObject("employees", employeeService.findAll());
        mv.addObject("employeeToAdd", new Employee());
        mv.addObject("offersEmployeeNot", offrN);
        mv.addObject("offersEmployeeYes", offersOfUser);

        mv.addObject("updateEmployee", updUser);
        return mv;
    } 

    @PostMapping("/updateEmployee")
    String updateEmployeeSave(@ModelAttribute Employee user, String offerToDelete, String offerToAdd){
        Employee updEmployee = employeeService.findById(user.getEmployee_id());
        updEmployee.setName(user.getName());
        if(offerToDelete != "" && offerToDelete !=null)
        {
            updEmployee.getOffers().remove(offerService.findById(Integer.valueOf(offerToDelete)));
            updEmployee.setOffers(updEmployee.getOffers());

        }
        if(offerToAdd != "" && offerToAdd !=null)
        {
            updEmployee.getOffers().add(offerService.findById(Integer.valueOf(offerToAdd)));
            updEmployee.setOffers(updEmployee.getOffers());

        }
        employeeService.update(updEmployee);
        return "redirect:/employee/config";
    }
   
    
}
