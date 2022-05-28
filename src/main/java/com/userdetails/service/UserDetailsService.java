package com.userdetails.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.userdetails.dto.UserDTO;
import com.userdetails.model.UserDetails;


public interface UserDetailsService {
    UserDetails getUserDetailsById(Long id);
    String createNewUser(UserDTO user) throws JsonProcessingException;
    String deleteUserById(Long id);
}
