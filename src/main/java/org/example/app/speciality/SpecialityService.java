package org.example.app.speciality;

import org.example.app.web.dto.SpecialityExpose;
import org.example.app.web.dto.SpecialityRequest;

import java.util.List;
import java.util.Set;

public interface SpecialityService {


    List<SpecialityExpose> getAllSpecialities();


    SpecialityExpose getSpeciality(Long id);

    SpecialityExpose createNewSpeciality(SpecialityRequest request);

    SpecialityExpose updateSpeciality(Long id, SpecialityRequest request);

    SpecialityExpose deleteSpeciality(Long id);

    Speciality getSpecialityByName(String specialityName);

    Set<Speciality> getSpecialitiesByTheirNames(List<String> specialities);

}
