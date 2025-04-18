package org.example.app.medicalVisit;


import jakarta.persistence.*;
import lombok.*;
import org.example.app.diagnose.Diagnose;
import org.example.app.doctor.Doctor;
import org.example.app.patient.Patient;
import org.example.app.sickLeave.SickLeave;
import org.example.app.user.BaseEntity;

import java.time.LocalDate;
import java.util.Set;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicalVisit extends BaseEntity {


    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    private LocalDate visitationDate;

    @Column(name = "treatment")
    private String treatment;


    @OneToOne
    @JoinColumn(name = "sick_leave_id", nullable = true)
    private SickLeave sickLeave;

    @ManyToMany
    @JoinTable(name = "medical_visits_diagnoses",
            joinColumns = @JoinColumn(name = "medical_visit_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "diagnose_id", referencedColumnName = "id"))
    private Set<Diagnose> diagnoses;
}
