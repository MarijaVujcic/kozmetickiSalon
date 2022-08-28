package com.seminar.kozmetickisalon.Controller;

package com.seminar.kozmetickisalon.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.seminar.kozmetickisalon.Model.Employee;
import com.seminar.kozmetickisalon.Model.User;
import com.seminar.kozmetickisalon.Repository.RoleRepository;
import com.seminar.kozmetickisalon.Repository.UserRepository;
import com.seminar.kozmetickisalon.Service.EmployeeService;
import com.seminar.kozmetickisalon.Service.OfferService;
import com.seminar.kozmetickisalon.Service.UserService;

@Controller
@RequestMapping("/employee")
public class  EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    OfferService offerService;

    @GetMapping("/config")
    ModelAndView showUserConfiguration(){
        ModelAndView mv = new ModelAndView("userConfiguration");
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
        ModelAndView mv = new ModelAndView("userConfiguration");
        Employee updUser = employeeService.findById(Integer.valueOf(id));
        mv.addObject("employees", employeeService.findAll());
        mv.addObject("employeeToAdd", new Employee());
        mv.addObject("offers", offerService.findAll());
        mv.addObject("updateEmployee", updUser);
        return mv;
    } 

    @PostMapping("/updateEmployee")
    String updateRoleSave(@ModelAttribute User user, String roleToUpdate){
        return "redirect:/user/config";
    }
   
    
}
