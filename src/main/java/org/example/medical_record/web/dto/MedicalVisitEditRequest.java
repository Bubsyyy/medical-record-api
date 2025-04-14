package org.example.medical_record.web.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.lang.annotation.After;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalVisitEditRequest {


    private String nameOfTheDiagnose;


    private String treatment;

    private boolean needSickLeave;


    private String reasonForSickLeave;

    private int days;

    private LocalDate startDate;


}
