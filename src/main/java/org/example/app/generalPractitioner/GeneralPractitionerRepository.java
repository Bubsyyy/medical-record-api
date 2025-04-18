package org.example.app.generalPractitioner;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeneralPractitionerRepository extends CrudRepository<GeneralPractitioner, Long> {

    GeneralPractitioner findGeneralPractitionerByRegistrationNumber(String registrationNumber);

    @Query(value = """
            SELECT gp.id, CONCAT(gp.firstName, ' ', gp.lastName), COUNT(p) \
            FROM GeneralPractitioner gp \
            LEFT JOIN gp.registeredPatients p \
            GROUP BY gp.id, gp.firstName, gp.lastName""")
    List<Object[]> countPatientsPerGP();

}
