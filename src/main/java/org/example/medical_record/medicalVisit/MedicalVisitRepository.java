package org.example.medical_record.medicalVisit;

import org.example.medical_record.patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MedicalVisitRepository extends JpaRepository<MedicalVisit, Long> {
    List<MedicalVisit> findMedicalVisitsByPatient(Patient patient);


    @Query("SELECT mv FROM MedicalVisit mv " +
            "WHERE mv.visitationDate BETWEEN :startDate AND :endDate " +
            "ORDER BY mv.visitationDate DESC")
    List<MedicalVisit> findVisitsWithinPeriod(@Param("startDate") LocalDate startDate,
                                              @Param("endDate") LocalDate endDate);


    @Query("SELECT mv.doctor, COUNT(mv.sickLeave) " +
            "FROM MedicalVisit mv " +
            "WHERE mv.sickLeave IS NOT NULL " +
            "GROUP BY mv.doctor " +
            "ORDER BY COUNT(mv.sickLeave) DESC")
    List<Object[]> findDoctorsWithMostSickLeaves();

}
