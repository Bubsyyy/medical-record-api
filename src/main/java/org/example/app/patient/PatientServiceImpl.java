package org.example.app.patient;


import org.example.app.event.UserRegisteredEventProducer;
import org.example.app.event.payload.UserRegisteredEvent;
import org.example.app.exception.PatientNotFoundException;
import org.example.app.generalPractitioner.GeneralPractitioner;
import org.example.app.generalPractitioner.GeneralPractitionerService;
import org.example.app.web.dto.PatientEditRequest;
import org.example.app.web.dto.PatientExpose;
import org.example.app.web.dto.PatientRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PasswordEncoder passwordEncoder;
    private final GeneralPractitionerService generalPractitionerService;
    private final UserRegisteredEventProducer userRegisteredEventProducer;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository,
                              PasswordEncoder passwordEncoder,
                              GeneralPractitionerService generalPractitionerService, UserRegisteredEventProducer userRegisteredEventProducer) {
        this.patientRepository = patientRepository;
        this.passwordEncoder = passwordEncoder;
        this.generalPractitionerService = generalPractitionerService;
        this.userRegisteredEventProducer = userRegisteredEventProducer;
    }

    @Override
    public List<PatientExpose> getAllPatients() {

        List<Patient> allPatients = patientRepository.findAll();



        return allPatients.stream().map(PatientMapper::toPatientExpose).toList();

    }

    @Override
    public PatientExpose getPatient(Long id) {

        Optional<Patient> patientOptional = patientRepository.findById(id);

        if (patientOptional.isEmpty()) {
            throw new PatientNotFoundException("There is no such patient with id:" + id);
        }

        Patient patient = patientOptional.get();

        return PatientMapper.toPatientExpose(patient);

    }

    @Override
    public PatientExpose createNewPatient(PatientRequest request) {

        Patient patient = PatientMapper.mapDtotoPatient(request);
        patient.setPassword(passwordEncoder.encode(request.getPassword()));
        patientRepository.save(patient);

        UserRegisteredEvent event = UserRegisteredEvent.builder()
                .userId(patient.getId())
                .createdOn(LocalDateTime.now())
                .build();
        userRegisteredEventProducer.sendEvent(event);



        return PatientMapper.toPatientExpose(patient);
    }

    @Override
    public PatientExpose updatePatientById(Long id, PatientEditRequest request) {

        Optional<Patient> patientOptional = patientRepository.findById(id);

        if (patientOptional.isEmpty()) {
            throw new PatientNotFoundException("There is no such patient with id:" + id);
        }

        Patient patient = patientOptional.get();
        GeneralPractitioner generalPractitioner = generalPractitionerService
                .getGeneralPractitionerByRegistrationNumber(request.getDoctorsRegistrationNumber());

        patient.setGeneralPractitioner(generalPractitioner);
        patientRepository.save(patient);


        return PatientMapper.toPatientExpose(patient);
    }

    @Override
    public Patient getPatientByNames(String firstName, String lastName) {


        return patientRepository.findByFirstNameAndLastName(firstName, lastName);

    }

    @Override
    public Patient getPatientByUsername(String username) {

        return patientRepository.findPatientByUsername(username);
    }

    @Override
    public List<PatientExpose> getAllPatientsWithDiagnoseByDiagnoseId(Long id) {

        List<Patient> patientsByDiagnoseId = patientRepository.findPatientsByDiagnoseId(id);

        return patientsByDiagnoseId.stream().map(PatientMapper::toPatientExpose).toList();
    }

    @Override
    public List<PatientExpose> getAllPatientsWithSpecificGeneralPractitioner(Long generalPractitionerId) {

        GeneralPractitioner generalPractitioner = this.generalPractitionerService.getGeneralPractitionerById(generalPractitionerId);

        List<Patient> patients = patientRepository.findPatientsByGeneralPractitioner(generalPractitioner);

        return patients.stream().map(PatientMapper::toPatientExpose).toList();
    }

    @Override
    public Patient getPatientById(Long patientId) {

        Optional<Patient> patientOptional = patientRepository.findById(patientId);

        if (patientOptional.isEmpty()) {
            throw new PatientNotFoundException("There is no such patient with id:" + patientId);
        }

        return patientOptional.get();
    }
}
