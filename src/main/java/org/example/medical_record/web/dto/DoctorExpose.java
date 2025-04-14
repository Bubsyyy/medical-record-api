package org.example.medical_record.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorExpose {

    private String firstName;
    private String lastName;
    private List<SpecialityExpose> specialties;
}
