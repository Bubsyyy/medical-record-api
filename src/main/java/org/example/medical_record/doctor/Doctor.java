package org.example.medical_record.doctor;

import jakarta.persistence.*;
import lombok.*;
import org.example.medical_record.medicalVisit.MedicalVisit;
import org.example.medical_record.speciality.Speciality;
import org.example.medical_record.user.User;

import java.util.Set;

@Builder
@Entity
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Doctor extends User {

    @Column(unique = true, nullable = false)
    private String registrationNumber;

    @ManyToMany
    @JoinTable(name = "doctors_specialities",
            joinColumns = @JoinColumn(name = "doctor_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "speciality_id", referencedColumnName = "id"))
    private Set<Speciality> specialities;

    @OneToMany(mappedBy = "doctor")
    private Set<MedicalVisit> medicalVisits;





}