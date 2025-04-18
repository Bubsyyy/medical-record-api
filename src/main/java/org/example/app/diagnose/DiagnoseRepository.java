package org.example.app.diagnose;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiagnoseRepository extends JpaRepository<Diagnose, Long> {

    Optional<Diagnose> findByName(String name);

    @Query("SELECT d FROM Diagnose d ORDER BY d.dateOfFoundation DESC LIMIT 1")
    Diagnose findLatestDiagnose();


}
