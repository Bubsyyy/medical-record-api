package org.example.app.medicalVisit;

import lombok.experimental.UtilityClass;
import org.example.app.web.dto.MedicalVisitExpose;

@UtilityClass
public class MedicalVisitMapper {

    public static MedicalVisitExpose mapVisitToExpose(MedicalVisit medicalVisit) {

        MedicalVisitExpose medicalVisitExpose = new MedicalVisitExpose();
        medicalVisitExpose.setVisitationDate(medicalVisit.getVisitationDate());
        medicalVisitExpose.setDoctorFirstName(medicalVisit.getDoctor().getFirstName());
        medicalVisitExpose.setPatientFirstName(medicalVisit.getPatient().getFirstName());
        medicalVisitExpose.setPatientLastName(medicalVisit.getPatient().getLastName());

        return medicalVisitExpose;

    }
}
