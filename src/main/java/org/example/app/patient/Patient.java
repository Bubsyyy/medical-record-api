package org.example.app.patient;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.app.generalPractitioner.GeneralPractitioner;
import org.example.app.medicalVisit.MedicalVisit;
import org.example.app.user.User;

import java.util.Set;

@Entity
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Patient extends User {
    @ManyToOne
    @JoinColumn(name = "gp_id")
    private GeneralPractitioner generalPractitioner;

    @Column(nullable = false)
    private boolean hasValidInsurance;

    @OneToMany(mappedBy = "patient")
    private Set<MedicalVisit> medicalVisits;


}
