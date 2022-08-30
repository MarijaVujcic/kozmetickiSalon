package com.seminar.kozmetickisalon.Repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.seminar.kozmetickisalon.Model.Reservations;

@Repository
public interface ReservationRepository extends JpaRepository<Reservations, Integer> {



    
}
