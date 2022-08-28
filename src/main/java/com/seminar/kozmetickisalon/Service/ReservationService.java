package com.seminar.kozmetickisalon.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seminar.kozmetickisalon.Model.Employee;
import com.seminar.kozmetickisalon.Model.Offer;
import com.seminar.kozmetickisalon.Model.Reservations;
import com.seminar.kozmetickisalon.Model.User;
import com.seminar.kozmetickisalon.Repository.ReservationRepository;

@Service
public class ReservationService {
    @Autowired
    ReservationRepository reservationRepository;

    public List<Reservations> findAll() {
        return reservationRepository.findAll();
    }


    public List<Reservations> findAllByUser(Integer u) {
        return reservationRepository.findAllByUserId(u).stream().toList();
    }
    public List<Reservations> findAllByOffer(Integer o) {

        return reservationRepository.findAllByOfferId(o).stream().toList();
    }


    public void delete(Integer valueOf) {
        reservationRepository.delete(reservationRepository.findById(valueOf).get());
    }

    
}
