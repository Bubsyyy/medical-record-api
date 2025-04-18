package org.example.app.generalPractitioner;

import org.example.app.web.dto.GeneralPractitionerRequest;
import org.example.app.web.dto.GpExpose;
import org.example.app.web.dto.PatientsCountPerGp;

import java.util.List;

public interface GeneralPractitionerService {
    GpExpose createNewGp(GeneralPractitionerRequest request);

    GeneralPractitioner getGeneralPractitionerByRegistrationNumber(String doctorsRegistrationNumber);

    GeneralPractitioner getGeneralPractitionerById(Long generalPractitionerId);

    List<PatientsCountPerGp> getPatientsCountPerGp();

}
