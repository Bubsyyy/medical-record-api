package org.example.medical_record.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorVisitsCount {
    private long id;
    private String names;
    private long visitsCount;
}
