package org.example.medical_record.sickLeave;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SickLeaveRepository extends JpaRepository<SickLeave, Long> {


    @Query("SELECT FUNCTION('MONTH', sl.startTime), COUNT(sl) " +
            "FROM SickLeave sl " +
            "GROUP BY FUNCTION('MONTH', sl.startTime) " +
            "ORDER BY COUNT(sl) DESC LIMIT 1")
    Object[] findMonthWithMostSickLeaves();


}
