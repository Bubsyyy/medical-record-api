package org.example.medical_record.generalPractitioner;

import org.example.medical_record.web.dto.GeneralPractitionerRequest;
import org.example.medical_record.web.dto.GpExpose;
import org.example.medical_record.web.dto.PatientsCountPerGp;

import java.util.List;

public interface GeneralPractitionerService {
    GpExpose createNewGp(GeneralPractitionerRequest request);

    GeneralPractitioner getGeneralPractitionerByRegistrationNumber(String doctorsRegistrationNumber);

    GeneralPractitioner getGeneralPractitionerById(Long generalPractitionerId);

    List<PatientsCountPerGp> getPatientsCountPerGp();

}
