package org.example.medical_record.medicalVisit;

import org.example.medical_record.web.dto.*;

import java.util.List;

public interface MedicalVisitService {
    MedicalVisitExpose createVisit(MedicalVisitCreationRequest request);

    List<MedicalVisitExpose> getAllVisits();

    MedicalVisitExpose getVisitById(Long id);

    MedicalVisitExpose updateMedicalVisit(Long id, MedicalVisitEditRequest request);

    MedicalVisitExpose deleteVisitById(Long id);

    List<MedicalVisitExpose> getHistoryOfPatientByHisUsername(String username);

    List<MedicalVisitExpose> getVisitHistoryForPeriod(VisitHistoryPeriodRequest request);

    List<DoctorsSickLeaves> getDoctorsWithSickLeaves();
}
