package org.example.app.generalPractitioner;

import lombok.experimental.UtilityClass;
import org.example.app.user.Role;
import org.example.app.web.dto.GeneralPractitionerRequest;
import org.example.app.web.dto.GpExpose;

import java.util.HashSet;

@UtilityClass
public class GeneralPractitionerMapper {

    public static GpExpose toGpExpose(GeneralPractitioner gp) {
        GpExpose gpExpose = new GpExpose();
        gpExpose.setFirstName(gp.getFirstName());
        gpExpose.setLastName(gp.getLastName());
        gpExpose.setProvidesHomeVisits(gp.isProvidesHomeVisits());

        return gpExpose;
    }

    public static GeneralPractitioner mapDtoToGeneralPractitioner(GeneralPractitionerRequest request) {
        GeneralPractitioner gp = new GeneralPractitioner();
        gp.setFirstName(request.getFirstName());
        gp.setLastName(request.getLastName());
        gp.setUsername(request.getUsername());
        gp.setPersonalNumber(request.getPersonalNumber());
        gp.setRegistrationNumber(request.getRegistrationNumber());
        gp.setRole(Role.DOCTOR);
        gp.setSpecialities(new HashSet<>());
        gp.setMedicalVisits(new HashSet<>());
        gp.setEmail(request.getEmail());
        gp.setPersonalNumber(request.getPersonalNumber());
        gp.setProvidesHomeVisits(request.isProvidesHomeVisits());


        return gp;

    }
}
