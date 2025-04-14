package org.example.medical_record.medicalVisit;


import jakarta.persistence.*;
import lombok.*;
import org.example.medical_record.diagnose.Diagnose;
import org.example.medical_record.doctor.Doctor;
import org.example.medical_record.patient.Patient;
import org.example.medical_record.sickLeave.SickLeave;
import org.example.medical_record.user.BaseEntity;

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
