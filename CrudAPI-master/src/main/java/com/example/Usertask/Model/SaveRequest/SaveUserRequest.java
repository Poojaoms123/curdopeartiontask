package com.example.Usertask.Model.SaveRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Getter
@Setter
@AllArgsConstructor
public class SaveUserRequest {

    private Long id;
    @Pattern(regexp = "^[a-zA-Z_]+$", message = "Name is invalid")
    @NotEmpty(message = "Name cannot be empty")
    private String name;


    private  String email;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 10, max = 10, message = "Password must be between 10  characters")
    private String password;

    private String city;
    private String state;
    private String country;

    @NotEmpty(message = "zipcode cannot be empty")
    @Size(min = 6, max = 6, message = "zipcode must be 6 digit")
    private String zipcode;

}
