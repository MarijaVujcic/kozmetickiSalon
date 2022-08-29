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

    @Query(value="select r from reservations r where r.offer.offer_id =:offer_id ", nativeQuery = true)
    Set<Reservations> findAllByOfferId(@Param("offer_id")Integer offer_id);

    @Query(value="select r from reservations r where r.user.user_id =:user_id ", nativeQuery = true)
    Set<Reservations> findAllByUserId(@Param("user_id")Integer user_id);

    @Query(value="select r from reservations r where r.isConfirmed =false ", nativeQuery = true)
    List<Reservations> finAllUnconfirmed();
    
}
