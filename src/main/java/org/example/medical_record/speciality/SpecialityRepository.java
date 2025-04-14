package org.example.medical_record.speciality;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecialityRepository extends JpaRepository<Speciality, Long> {


    Speciality findByName(String name);

    List<Speciality> findByNameIn(List<String> names);

}
