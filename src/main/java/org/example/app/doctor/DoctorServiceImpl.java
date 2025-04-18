package org.example.app.doctor;

import org.example.app.event.UserRegisteredEventProducer;
import org.example.app.event.payload.UserRegisteredEvent;
import org.example.app.exception.DoctorNotFoundException;
import org.example.app.speciality.Speciality;
import org.example.app.speciality.SpecialityService;
import org.example.app.web.dto.DoctorEditRequest;
import org.example.app.web.dto.DoctorExpose;
import org.example.app.web.dto.DoctorRequest;
import org.example.app.web.dto.DoctorVisitsCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final SpecialityService specialityService;
    private final PasswordEncoder passwordEncoder;
    private final UserRegisteredEventProducer userRegisteredEventProducer;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository, SpecialityService specialityService, PasswordEncoder passwordEncoder, UserRegisteredEventProducer userRegisteredEventProducer) {
        this.doctorRepository = doctorRepository;
        this.specialityService = specialityService;
        this.passwordEncoder = passwordEncoder;
        this.userRegisteredEventProducer = userRegisteredEventProducer;
    }

    @Override
    public List<DoctorExpose> getAllDoctors() {

        List<Doctor> doctors = doctorRepository.findAll();

        return doctors.stream().map(DoctorMapper::mapDoctorToExpose).toList();
    }

    @Override
    public DoctorExpose getDoctor(Long id) {

        Optional<Doctor> doctorOptional = doctorRepository.findById(id);
        if (doctorOptional.isEmpty()) {
            throw new DoctorNotFoundException("There is no such doctor with id: " + id);
        }

        Doctor doctor = doctorOptional.get();


        return DoctorMapper.mapDoctorToExpose(doctor);
    }

    @Override
    public DoctorExpose createNewDoctor(DoctorRequest request) {


        Doctor doctor = DoctorMapper.mapDtoToDoctor(request);
        doctor.setPassword(passwordEncoder.encode(request.getPassword()));

        List<String> specialities = request.getSpecialities();

        for (String specialityName : specialities) {

            Speciality speciality = specialityService.getSpecialityByName(specialityName);
            doctor.getSpecialities().add(speciality);

        }


        doctorRepository.save(doctor);

        UserRegisteredEvent event = UserRegisteredEvent.builder()
                .userId(doctor.getId())
                .createdOn(LocalDateTime.now())
                .build();
        userRegisteredEventProducer.sendEvent(event);


        return DoctorMapper.mapDoctorToExpose(doctor);
    }

    @Override
    public DoctorExpose updateDoctorById(Long id, DoctorEditRequest request) {

        Optional<Doctor> doctorOptional = doctorRepository.findById(id);
        if (doctorOptional.isEmpty()) {
            throw new DoctorNotFoundException("There is no such doctor with id: " + id);
        }

        Doctor doctor = doctorOptional.get();
        doctor.setEmail(request.getEmail());
        doctorRepository.save(doctor);

        return DoctorMapper.mapDoctorToExpose(doctor);
    }

    @Override
    public DoctorExpose deleteDoctorById(Long id) {

        Optional<Doctor> doctorOptional = doctorRepository.findById(id);
        if (doctorOptional.isEmpty()) {
            throw new DoctorNotFoundException("There is no such doctor with id: " + id);
        }

        Doctor doctor = doctorOptional.get();
        doctorRepository.delete(doctor);


        return DoctorMapper.mapDoctorToExpose(doctor);
    }

    @Override
    public Doctor getDoctorByNames(String firstName, String lastName) {


        return doctorRepository.findByFirstNameAndLastName(firstName, lastName);

    }

    @Override
    public Doctor getDoctorById(Long doctorId) {

        Optional<Doctor> doctorOptional = doctorRepository.findById(doctorId);
        if (doctorOptional.isEmpty()) {
            throw new DoctorNotFoundException("There is no such doctor with id: " + doctorId);
        }

        return doctorOptional.get();
    }

    @Override
    public List<DoctorVisitsCount> getCountOfAllDoctorVisits() {

        List<Object[]> doctorVisits = doctorRepository.countVisitsPerDoctor();
        List<DoctorVisitsCount> doctorVisitsCountsList = new ArrayList<>();

        for (Object[] doctorVisit : doctorVisits) {

            long id = (Long) doctorVisit[0];
            String names = (String) doctorVisit[1];
            long count = (Long) doctorVisit[2];
            DoctorVisitsCount doctorVisitsCount = new DoctorVisitsCount(id, names, count);
            doctorVisitsCountsList.add(doctorVisitsCount);


        }


        return doctorVisitsCountsList;
    }
}
