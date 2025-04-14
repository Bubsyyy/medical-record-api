package org.example.medical_record.speciality;

import jakarta.persistence.*;
import lombok.*;
import org.example.medical_record.doctor.Doctor;
import org.example.medical_record.user.BaseEntity;

import java.util.Set;

@Entity
@Table(name = "specialities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Speciality extends BaseEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "specialities")
    private Set<Doctor> doctor;

}
