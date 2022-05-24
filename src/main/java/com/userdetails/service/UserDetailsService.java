package com.userdetails.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.userdetails.dto.UserDTO;
import com.userdetails.model.UserDetails;
import com.userdetails.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public interface UserDetailsService {
    UserDetails getUserDetailsById(Long id);
    String createNewUser(UserDTO user) throws JsonProcessingException;
    String deleteUserById(Long id);
}
