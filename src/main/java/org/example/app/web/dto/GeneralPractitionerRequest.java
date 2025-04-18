package org.example.app.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneralPractitionerRequest {


    @NotBlank
    private String firstName;


    @NotBlank
    private String lastName;

    @Size(min = 10, max = 10, message = "Personal Number must be 10 symbols")
    private String personalNumber;

    @Email
    private String email;


    @NotBlank
    private String username;


    @NotBlank
    private String password;


    @NotBlank
    private String registrationNumber;

    private List<String> specialities;


    private boolean providesHomeVisits;
}
