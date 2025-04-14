package org.example.medical_record.web.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalVisitExpose {

    private String patientFirstName;
    private String patientLastName;
    private String doctorFirstName;
    private LocalDate visitationDate;

}
