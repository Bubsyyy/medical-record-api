package org.example.app.patient;

import lombok.experimental.UtilityClass;
import org.example.app.user.Role;
import org.example.app.web.dto.PatientExpose;
import org.example.app.web.dto.PatientRequest;

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
