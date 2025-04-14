package org.example.medical_record.patient;

import lombok.experimental.UtilityClass;
import org.example.medical_record.user.Role;
import org.example.medical_record.web.dto.PatientExpose;
import org.example.medical_record.web.dto.PatientRequest;

@UtilityClass
public class PatientMapper {

    public static PatientExpose toPatientExpose(Patient patient) {
        PatientExpose patientExpose = new PatientExpose();

        patientExpose.setFirstName(patient.getFirstName());
        patientExpose.setLastName(patient.getLastName());
        patientExpose.setHasValidInsurance(patient.isHasValidInsurance());

        return patientExpose;
    }


    public static Patient mapDtotoPatient(PatientRequest request) {

        Patient patient = new Patient();
        patient.setFirstName(request.getFirstName());
        patient.setLastName(request.getLastName());
        patient.setPersonalNumber(request.getPersonalNumber());
        patient.setRole(Role.PATIENT);
        patient.setEmail(request.getEmail());
        patient.setUsername(request.getUsername());
        patient.setHasValidInsurance(request.isHasValidInsurance());

        return patient;
    }
}
