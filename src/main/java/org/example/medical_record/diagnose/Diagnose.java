package org.example.medical_record.diagnose;

import jakarta.persistence.*;
import lombok.*;
import org.example.medical_record.medicalVisit.MedicalVisit;
import org.example.medical_record.user.BaseEntity;

import java.time.LocalDate;
import java.util.Set;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Diagnose extends BaseEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    private LocalDate dateOfFoundation;

    //TODO add LocalDate date of foundation

    @ManyToMany(mappedBy = "diagnoses")
    private Set<MedicalVisit> medicalVisits;
}
