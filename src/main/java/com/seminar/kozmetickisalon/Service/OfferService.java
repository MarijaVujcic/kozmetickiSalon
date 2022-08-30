package com.seminar.kozmetickisalon.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seminar.kozmetickisalon.Model.Offer;
import com.seminar.kozmetickisalon.Repository.EmployeeRepository;
import com.seminar.kozmetickisalon.Repository.OfferRepository;

@Service
public class OfferService {
    
    @Autowired
    OfferRepository offerRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Offer> findAll() {
        return offerRepository.findAll();
    }

    public void deleteOffer(Integer valueOf) {
        Offer toDelete = offerRepository.findById(valueOf).get();
        offerRepository.delete(toDelete);
    }

    public void createOffer(Offer offer) {
        Offer make = new Offer(offer);
        offerRepository.save(make);
    }

    public Offer findById(Integer valueOf) {
        return offerRepository.findById(valueOf).get();
    }

    public void updateOffer(Offer offer) {
        Offer update = offerRepository.findById(offer.getOffer_id()).get();
        update.setNameOfOffer(offer.getNameOfOffer());
        update.setPrice(offer.getPrice());
        offerRepository.save(update);
    }

}
