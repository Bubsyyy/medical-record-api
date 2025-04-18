package org.example.app.diagnose;

import lombok.experimental.UtilityClass;
import org.example.app.web.dto.DiagnoseExpose;

@UtilityClass
public class DiagnoseMapper {

    public static DiagnoseExpose mapDiagnoseToExpose(Diagnose diagnose) {

        return new DiagnoseExpose(diagnose.getName());


    }

}
