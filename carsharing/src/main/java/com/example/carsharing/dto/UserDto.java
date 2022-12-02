package com.example.carsharing.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String phone;
    private String firstName;
    private String lastName;
    private String password;
    private String passwordSeries;
    private String passwordNumber;
}
