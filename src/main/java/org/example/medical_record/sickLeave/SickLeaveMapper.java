package org.example.medical_record.sickLeave;

import lombok.experimental.UtilityClass;
import org.example.medical_record.web.dto.SickLeaveExpose;

@UtilityClass
public class SickLeaveMapper {

    public static SickLeaveExpose mapSickLeaveToExpose(SickLeave sickLeave){

        SickLeaveExpose sickLeaveExpose = new SickLeaveExpose();
        sickLeaveExpose.setReason(sickLeave.getReason());
        sickLeaveExpose.setStartDate(sickLeave.getStartTime());
        sickLeaveExpose.setEndDate(sickLeave.getEndTime());

        return sickLeaveExpose;

    }
}
