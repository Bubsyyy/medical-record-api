package org.example.medical_record.diagnose;

import org.example.medical_record.web.dto.DiagnoseCreationRequest;
import org.example.medical_record.web.dto.DiagnoseExpose;

import java.util.List;

public interface DiagnoseService {


    Diagnose getDiagnoseByName(String diagnoseName);

    List<DiagnoseExpose> getAllDiagnoses();

    DiagnoseExpose getDiagnosesById(Long id);

    DiagnoseExpose createNewDiagnose(DiagnoseCreationRequest request);

    DiagnoseExpose deleteDiagnoseById(Long id);

    DiagnoseExpose getLastDiagnose();

}
