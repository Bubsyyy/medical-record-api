package org.example.app.sickLeave;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;
import org.example.app.medicalVisit.MedicalVisit;
import org.example.app.user.BaseEntity;

import java.time.LocalDate;

@Builder
@Entity
@Table(name = "sick_leaves")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SickLeave extends BaseEntity {


    @Column(nullable = false)
    private LocalDate startTime;

    @Column(nullable = false)
    private LocalDate endTime;

    @Column(nullable = false)
    private String reason;

    @OneToOne(mappedBy = "sickLeave")
    private MedicalVisit medicalVisit;
}

