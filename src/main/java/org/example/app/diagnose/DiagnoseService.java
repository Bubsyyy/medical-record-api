package org.example.app.diagnose;

import org.example.app.web.dto.DiagnoseCreationRequest;
import org.example.app.web.dto.DiagnoseExpose;

import java.util.List;

public interface DiagnoseService {


    Diagnose getDiagnoseByName(String diagnoseName);

    List<DiagnoseExpose> getAllDiagnoses();

    DiagnoseExpose getDiagnosesById(Long id);

    DiagnoseExpose createNewDiagnose(DiagnoseCreationRequest request);

    DiagnoseExpose deleteDiagnoseById(Long id);

    DiagnoseExpose getLastDiagnose();

}
