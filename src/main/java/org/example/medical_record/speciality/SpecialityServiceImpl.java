package org.example.medical_record.speciality;

import org.example.medical_record.exception.SpecialityNotFoundException;
import org.example.medical_record.web.dto.SpecialityExpose;
import org.example.medical_record.web.dto.SpecialityRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SpecialityServiceImpl implements SpecialityService {

    private final SpecialityRepository specialityRepository;

    @Autowired
    public SpecialityServiceImpl(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }


    @Override
    public List<SpecialityExpose> getAllSpecialities() {

        return specialityRepository.findAll()
                .stream().map(speciality -> new SpecialityExpose(speciality.getName()))
                .toList();


    }

    @Override
    public SpecialityExpose getSpeciality(Long id) {

        Optional<Speciality> specialityOptional = specialityRepository.findById(id);

        if(specialityOptional.isEmpty()) {
            throw new SpecialityNotFoundException("There is no such speciality with id: " + id);
        }

        Speciality speciality = specialityOptional.get();

        return new SpecialityExpose(speciality.getName());

    }

    @Override
    public SpecialityExpose createNewSpeciality(SpecialityRequest request) {

        Speciality speciality = Speciality.builder().name(request.getName()).build();
        specialityRepository.save(speciality);

        SpecialityExpose specialityExpose = new SpecialityExpose(speciality.getName());

        return specialityExpose;
    }

    @Override
    public SpecialityExpose updateSpeciality(Long id, SpecialityRequest request) {

        Optional<Speciality> specialityOptional = specialityRepository.findById(id);
        if(specialityOptional.isEmpty()) {
            throw new SpecialityNotFoundException("There is no such speciality with id: " + id);
        }

        Speciality speciality = specialityOptional.get();
        speciality.setName(request.getName());
        specialityRepository.save(speciality);

        return new SpecialityExpose(speciality.getName());

    }

    @Override
    public SpecialityExpose deleteSpeciality(Long id) {

        Optional<Speciality> specialityOptional = specialityRepository.findById(id);
        if(specialityOptional.isEmpty()) {
            throw new SpecialityNotFoundException("There is no such speciality with id: " + id);
        }

        Speciality speciality = specialityOptional.get();
        specialityRepository.delete(speciality);

        return new SpecialityExpose(speciality.getName());

    }

    @Override
    public Speciality getSpecialityByName(String specialityName) {

        Speciality speciality = specialityRepository.findByName(specialityName);

        return speciality;
    }

    @Override
    public Set<Speciality> getSpecialitiesByTheirNames(List<String> specialityNames) {

        List<Speciality> specialities = specialityRepository.findByNameIn(specialityNames);

        if(specialities.isEmpty()) {
            throw new SpecialityNotFoundException("There is no such speciality with names: " + String.join(", ", specialityNames));
        }



        return new HashSet<>(specialities);
    }
}
