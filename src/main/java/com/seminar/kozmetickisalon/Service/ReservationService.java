package com.seminar.kozmetickisalon.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.seminar.kozmetickisalon.DTO.ReservationDTO;
import com.seminar.kozmetickisalon.Model.Employee;
import com.seminar.kozmetickisalon.Model.Offer;
import com.seminar.kozmetickisalon.Model.Reservations;
import com.seminar.kozmetickisalon.Model.User;
import com.seminar.kozmetickisalon.Repository.OfferRepository;
import com.seminar.kozmetickisalon.Repository.ReservationRepository;
import com.seminar.kozmetickisalon.Repository.UserRepository;

@Service
public class ReservationService {
    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    OfferRepository offerRepository;
    @Autowired
    UserRepository userRepository;

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


    public List<Reservations> finAllUnconfirmed() {
        return reservationRepository.finAllUnconfirmed();
    }


    public void setConfirmed(Integer valueOf) {
        Reservations r = reservationRepository.findById(valueOf).get();
        r.setIsConfirmed(true);
        reservationRepository.save(r);
    }

    public void saveReservation(ReservationDTO r){
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        Reservations newR = new Reservations();
        newR.setIsConfirmed(false);
        newR.setIsCancled(false);

        newR.setOffer(offerRepository.findById(r.getOfferId()).get());
        newR.setUser(userRepository.findByEmail(loggedInUser.getName()).get(0));
        newR.setPhoneNumber(r.getPhoneNumber());
        newR.setReservationDate(r.getDate());
        newR.setReservationTime(r.getTime());
        reservationRepository.save(newR);
    }


    public List<Reservations> getBuisyTimeDate(int month, int day) {
        List<Reservations> reservation = this.findAll();
        List<Reservations> reservationFree = new ArrayList<Reservations>();
        reservation.forEach((r) ->{
            Calendar calendar = new GregorianCalendar();
            calendar.setTime( r.getReservationDate());
            int monthR = calendar.get(Calendar.MONTH) + 1;
            int dayR = calendar.get(Calendar.DAY_OF_MONTH);
            
            if(dayR == day && monthR == month && r.isIsConfirmed()){
                reservationFree.add(r);

            }
        });
        return reservationFree;
    }

    

    
}
