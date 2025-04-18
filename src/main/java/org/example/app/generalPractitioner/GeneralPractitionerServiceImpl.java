package org.example.app.generalPractitioner;

import org.example.app.event.UserRegisteredEventProducer;
import org.example.app.event.payload.UserRegisteredEvent;
import org.example.app.exception.DoctorNotFoundException;
import org.example.app.speciality.Speciality;
import org.example.app.speciality.SpecialityService;
import org.example.app.web.dto.GeneralPractitionerRequest;
import org.example.app.web.dto.GpExpose;
import org.example.app.web.dto.PatientsCountPerGp;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class GeneralPractitionerServiceImpl implements GeneralPractitionerService{

    private final GeneralPractitionerRepository generalPractitionerRepository;
    private final PasswordEncoder passwordEncoder;
    private final SpecialityService specialityService;
    private final UserRegisteredEventProducer userRegisteredEventProducer;



    public GeneralPractitionerServiceImpl(GeneralPractitionerRepository generalPractitionerRepository
            , PasswordEncoder passwordEncoder
            , SpecialityService specialityService, UserRegisteredEventProducer userRegisteredEventProducer) {
        this.generalPractitionerRepository = generalPractitionerRepository;
        this.passwordEncoder = passwordEncoder;
        this.specialityService = specialityService;

        this.userRegisteredEventProducer = userRegisteredEventProducer;
    }



    @Override
    @Transactional
    public GpExpose createNewGp(GeneralPractitionerRequest request) {

        GeneralPractitioner generalPractitioner = GeneralPractitionerMapper.mapDtoToGeneralPractitioner(request);
        generalPractitioner.setPassword(passwordEncoder.encode(request.getPassword()));

        Set<Speciality> specialities = specialityService.getSpecialitiesByTheirNames(request.getSpecialities());
        generalPractitioner.setSpecialities(specialities);

        generalPractitionerRepository.save(generalPractitioner);

        UserRegisteredEvent event = UserRegisteredEvent.builder()
                .userId(generalPractitioner.getId())
                .createdOn(LocalDateTime.now())
                .build();
        userRegisteredEventProducer.sendEvent(event);



        return GeneralPractitionerMapper.toGpExpose(generalPractitioner);
    }

    @Override
    public GeneralPractitioner getGeneralPractitionerByRegistrationNumber(String doctorsRegistrationNumber) {


        return generalPractitionerRepository.findGeneralPractitionerByRegistrationNumber(doctorsRegistrationNumber);
    }

    @Override
    public GeneralPractitioner getGeneralPractitionerById(Long generalPractitionerId) {

        Optional<GeneralPractitioner> generalPractitionerOptional = generalPractitionerRepository.findById(generalPractitionerId);
        if (generalPractitionerOptional.isEmpty()) {
            throw new DoctorNotFoundException("There is no suck general practitioner with id " + generalPractitionerId);
        }


        return generalPractitionerOptional.get();

    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public List<PatientsCountPerGp> getPatientsCountPerGp() {

        List<Object[]> countPatientsPerGP = generalPractitionerRepository.countPatientsPerGP();
        List<PatientsCountPerGp> patientsCountPerGpList = new ArrayList<>();

        for (Object[] object : countPatientsPerGP) {

            long doctorId = (Long) object[0];
            String doctorName = (String) object[1];
            long patientCount = (Long) object[2];
            PatientsCountPerGp dto = new PatientsCountPerGp(doctorId, doctorName, patientCount);
            patientsCountPerGpList.add(dto);


        }


        return patientsCountPerGpList;
    }
}
