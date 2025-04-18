package org.example.app.diagnose;

import jakarta.persistence.*;
import lombok.*;
import org.example.app.medicalVisit.MedicalVisit;
import org.example.app.user.BaseEntity;

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
