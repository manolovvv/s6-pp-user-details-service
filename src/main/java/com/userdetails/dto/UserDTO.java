package com.userdetails.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String firstName;
    private String familyName;
    private String gender;
    private String telephoneNumber;
    private String email;
    private String password;
    private String role;
}
