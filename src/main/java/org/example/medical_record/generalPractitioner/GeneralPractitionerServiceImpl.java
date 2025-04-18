package org.example.medical_record.generalPractitioner;

import org.example.medical_record.doctor.Doctor;
import org.example.medical_record.doctor.DoctorRepository;
import org.example.medical_record.doctor.DoctorService;
import org.example.medical_record.exception.DoctorNotFoundException;
import org.example.medical_record.patient.Patient;
import org.example.medical_record.speciality.Speciality;
import org.example.medical_record.speciality.SpecialityService;
import org.example.medical_record.web.dto.GeneralPractitionerRequest;
import org.example.medical_record.web.dto.GpExpose;
import org.example.medical_record.web.dto.PatientsCountPerGp;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class GeneralPractitionerServiceImpl implements GeneralPractitionerService{

    private final GeneralPractitionerRepository generalPractitionerRepository;
    private final PasswordEncoder passwordEncoder;
    private final SpecialityService specialityService;



    public GeneralPractitionerServiceImpl(GeneralPractitionerRepository generalPractitionerRepository
            , PasswordEncoder passwordEncoder
            , SpecialityService specialityService) {
        this.generalPractitionerRepository = generalPractitionerRepository;
        this.passwordEncoder = passwordEncoder;
        this.specialityService = specialityService;

    }



    @Override
    @Transactional
    public GpExpose createNewGp(GeneralPractitionerRequest request) {

        GeneralPractitioner generalPractitioner = GeneralPractitionerMapper.mapDtoToGeneralPractitioner(request);
        generalPractitioner.setPassword(passwordEncoder.encode(request.getPassword()));

        Set<Speciality> specialities = specialityService.getSpecialitiesByTheirNames(request.getSpecialities());
        generalPractitioner.setSpecialities(specialities);

        generalPractitionerRepository.save(generalPractitioner);



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
