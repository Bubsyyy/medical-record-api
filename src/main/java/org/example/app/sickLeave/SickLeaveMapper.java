package org.example.app.sickLeave;

import lombok.experimental.UtilityClass;
import org.example.app.web.dto.SickLeaveExpose;

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
