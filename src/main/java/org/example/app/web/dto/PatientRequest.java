package org.example.app.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientRequest {


    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Size(min = 10, max = 10, message = "Personal Number must be 10 symbols")
    private String personalNumber;

    @Email
    private String email;

    @NotBlank
    private String username;

    @NotBlank
    private String password;


    private boolean hasValidInsurance;
}
