package org.example.app.sickLeave;

import org.example.app.web.dto.MonthWithSickLeaves;
import org.example.app.web.dto.SickLeaveEditRequest;
import org.example.app.web.dto.SickLeaveExpose;
import org.example.app.web.dto.SickLeaveRequest;

import java.util.List;

public interface SickLeaveService {
    SickLeave initializeSickLeave(SickLeaveRequest sickLeaveRequest);

    List<SickLeaveExpose> getAllSickLeaves();

    SickLeaveExpose getSickLeave(Long id);

    SickLeaveExpose deleteSickLeave(Long id);

    MonthWithSickLeaves getMonthWithMostSickLeaveCount();

    SickLeaveExpose updateSickLeave(Long id, SickLeaveEditRequest request);
}
