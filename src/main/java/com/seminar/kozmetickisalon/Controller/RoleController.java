package com.seminar.kozmetickisalon.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.seminar.kozmetickisalon.Model.Role;
import com.seminar.kozmetickisalon.Repository.RoleRepository;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/config")
    ModelAndView showRoleConfiguration(){
        ModelAndView mv = new ModelAndView("roleConfiguration");
        mv.addObject("roles", roleRepository.findAll());
        mv.addObject("roleToAdd", new Role());
        return mv;
    }

    @PostMapping("/updateRole/")
    ModelAndView updateRoleShow(String id){
        ModelAndView mv = new ModelAndView("roleConfiguration");
        Role updRole = roleRepository.findById(Integer.valueOf(id)).get();
        mv.addObject("roles", roleRepository.findAll());
        mv.addObject("roleToAdd", new Role());
        mv.addObject("updateRole", updRole);
        return mv;
    } 

    @PostMapping("/updateRole")
    String updateRoleSave(@ModelAttribute Role role){
        Role updRole = roleRepository.findById(role.getRole_id()).get();
        updRole.setName(role.getName());
        roleRepository.save(updRole);
        return "redirect:/role/config";
    }

    @GetMapping("/deleteRole/{id}")
    String deleteRole(@PathVariable String id){
        roleRepository.delete(roleRepository.findById(Integer.valueOf(id)).get());
        return "redirect:/role/config";
    }

    @PostMapping("/createRole")
    String createNewRole(@ModelAttribute Role role){
        Role makeRole = new Role(role.getName());
        roleRepository.save(makeRole);
        roleRepository.flush();
        return "redirect:/role/config";
    }

}
