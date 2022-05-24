package com.userdetails.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDetailsResponse {
    private String firstName;
    private String familyName;
    private String gender;
    private String telephoneNumber;
}
