package com.seminar.kozmetickisalon.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.seminar.kozmetickisalon.Model.Employee;
import com.seminar.kozmetickisalon.Model.Offer;
import com.seminar.kozmetickisalon.Model.Reservations;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer>{

    
}
