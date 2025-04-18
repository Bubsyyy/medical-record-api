package org.example.app.speciality;

import jakarta.persistence.*;
import lombok.*;
import org.example.app.doctor.Doctor;
import org.example.app.user.BaseEntity;

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
