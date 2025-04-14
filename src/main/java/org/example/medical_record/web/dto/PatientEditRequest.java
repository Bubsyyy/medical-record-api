package org.example.medical_record.web.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientEditRequest {

    @Email
    private String email;

    private String doctorsRegistrationNumber;

}
