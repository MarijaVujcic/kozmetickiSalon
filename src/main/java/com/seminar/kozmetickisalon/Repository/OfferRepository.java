package com.seminar.kozmetickisalon.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seminar.kozmetickisalon.Model.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer>{

    
}
