package com.userdetails.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.userdetails.dto.UserDTO;
import com.userdetails.dto.UserDetailsResponse;
import com.userdetails.model.UserDetails;
import com.userdetails.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserDetailsController {

    @Autowired
    UserDetailsService service;

    @GetMapping(value = "/{id}")
    public UserDetailsResponse getUserDetailsById(@PathVariable Long id){
        UserDetails userDetails = service.getUserDetailsById(id);
        try {
            return new UserDetailsResponse(userDetails.getFirstName(), userDetails.getFamilyName(), userDetails.getGender(), userDetails.getTelephoneNumber());
            }
        catch (Exception ex){
              return null;
            }
    }

    @PostMapping
    public String createUser(@RequestBody UserDTO user) throws JsonProcessingException {
        return service.createNewUser(user);
    }

    @DeleteMapping(value="/{id}")
    public String deleteUser(@PathVariable Long id){
        return service.deleteUserById(id);
    }

}
