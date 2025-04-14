package org.example.medical_record.doctor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Doctor findByFirstNameAndLastName(String firstName, String lastName);


    @Query("SELECT d.id, CONCAT(d.firstName, ' ', d.lastName), COUNT(mv) " +
            "FROM Doctor d " +
            "LEFT JOIN d.medicalVisits mv " +
            "GROUP BY d.id, d.firstName, d.lastName " +
            "ORDER BY COUNT(mv) DESC")
    List<Object[]> countVisitsPerDoctor();

}
