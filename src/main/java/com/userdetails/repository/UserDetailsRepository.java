package com.userdetails.repository;

import com.userdetails.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
}
