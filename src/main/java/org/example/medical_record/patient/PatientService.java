package org.example.medical_record.patient;


import jakarta.validation.Valid;
import org.example.medical_record.web.dto.PatientEditRequest;
import org.example.medical_record.web.dto.PatientExpose;
import org.example.medical_record.web.dto.PatientRequest;

import java.util.List;

public interface PatientService {
    List<PatientExpose> getAllPatients();

    PatientExpose getPatient(Long id);

    PatientExpose createNewPatient(PatientRequest request);


    PatientExpose updatePatientById(Long id, PatientEditRequest request);

    Patient getPatientByNames(String firstName, String lastName);

    Patient getPatientByUsername(String username);

    List<PatientExpose> getAllPatientsWithDiagnoseByDiagnoseId(Long id);

    List<PatientExpose> getAllPatientsWithSpecificGeneralPractitioner(Long generalPractitionerId);

    Patient getPatientById(Long patientId);
}
