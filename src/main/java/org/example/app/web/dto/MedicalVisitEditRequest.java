package org.example.app.web.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
