package org.example.medical_record.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientsCountPerGp {
    private Long id;
    private String gpName;
    private Long patientsCount;
}
