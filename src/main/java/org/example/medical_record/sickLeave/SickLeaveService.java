package org.example.medical_record.sickLeave;

import org.example.medical_record.web.dto.MonthWithSickLeaves;
import org.example.medical_record.web.dto.SickLeaveEditRequest;
import org.example.medical_record.web.dto.SickLeaveExpose;
import org.example.medical_record.web.dto.SickLeaveRequest;

import java.util.List;

public interface SickLeaveService {
    SickLeave initializeSickLeave(SickLeaveRequest sickLeaveRequest);

    List<SickLeaveExpose> getAllSickLeaves();

    SickLeaveExpose getSickLeave(Long id);

    SickLeaveExpose deleteSickLeave(Long id);

    MonthWithSickLeaves getMonthWithMostSickLeaveCount();

    SickLeaveExpose updateSickLeave(Long id, SickLeaveEditRequest request);
}
