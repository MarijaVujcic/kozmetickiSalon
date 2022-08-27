package com.seminar.kozmetickisalon.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seminar.kozmetickisalon.Model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);
	
}

