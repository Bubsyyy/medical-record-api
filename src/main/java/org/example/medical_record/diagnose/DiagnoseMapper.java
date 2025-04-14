package org.example.medical_record.diagnose;

import lombok.experimental.UtilityClass;
import org.example.medical_record.web.dto.DiagnoseExpose;

@UtilityClass
public class DiagnoseMapper {

    public static DiagnoseExpose mapDiagnoseToExpose(Diagnose diagnose) {

        return new DiagnoseExpose(diagnose.getName());


    }

}
