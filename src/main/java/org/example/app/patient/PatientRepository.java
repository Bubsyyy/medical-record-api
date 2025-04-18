package org.example.app.patient;

import org.example.app.generalPractitioner.GeneralPractitioner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {


    Patient findByFirstNameAndLastName(String firstName, String lastName);


    Patient findPatientByUsername(String username);

    @Query("SELECT DISTINCT mv.patient FROM MedicalVisit mv " +
            "JOIN mv.diagnoses d " +
            "WHERE d.id = :diagnoseId")
    List<Patient> findPatientsByDiagnoseId(@Param("diagnoseId") Long diagnoseId);

    List<Patient> findPatientsByGeneralPractitioner(GeneralPractitioner generalPractitioner);


}
