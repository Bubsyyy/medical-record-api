package org.example.medical_record.generalPractitioner;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.medical_record.doctor.Doctor;
import org.example.medical_record.patient.Patient;

import java.util.Set;

@Entity
@Table(name = "general_practitioners")
@PrimaryKeyJoinColumn(name = "doctor_id")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GeneralPractitioner extends Doctor {
    @OneToMany(mappedBy = "generalPractitioner")
    private Set<Patient> registeredPatients;


    @Column(nullable = false)
    private boolean providesHomeVisits;



}
