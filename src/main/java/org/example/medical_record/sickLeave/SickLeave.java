package org.example.medical_record.sickLeave;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;
import org.example.medical_record.medicalVisit.MedicalVisit;
import org.example.medical_record.user.BaseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

