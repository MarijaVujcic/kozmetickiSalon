package com.seminar.kozmetickisalon.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.seminar.kozmetickisalon.Model.*;
import com.seminar.kozmetickisalon.Repository.OfferRepository;
import com.seminar.kozmetickisalon.Service.OfferService;

@Controller
@RequestMapping("/offer")
public class OfferController {
    @Autowired
    OfferRepository offerRepository;

    @Autowired
    OfferService offerService;


    @GetMapping("/config")
    ModelAndView showUserConfiguration(){
        ModelAndView mv = new ModelAndView("offerConfiguration");
        mv.addObject("offers", offerRepository.findAll());
        mv.addObject("offerToAdd", new Offer());
        return mv;
    }

    @GetMapping("/deleteOffer/{id}")
    String deleteOffer(@PathVariable String id){
        offerService.deleteOffer(Integer.valueOf(id));
        return "redirect:/offer/config";
    }

    @PostMapping("/addOffer")
    String createNewOffer(@ModelAttribute Offer offer){
        offerService.createOffer(offer);
        return "redirect:/offer/config";
    }

    @GetMapping("/updateOffer/{id}")
    ModelAndView updateUserShow(@PathVariable String id){
        ModelAndView mv = new ModelAndView("offerConfiguration");
        Offer updOffer = offerService.findById(Integer.valueOf(id));
        mv.addObject("offers", offerRepository.findAll());
        mv.addObject("offerToAdd", new Offer());
        mv.addObject("updateOffer", updOffer);
        return mv;
    } 

    @PostMapping("/updateOffer")
    String updateRoleSave(@ModelAttribute Offer offer){
        offerService.updateOffer(offer);
        return "redirect:/offer/config";
    }
   
   
    
}
