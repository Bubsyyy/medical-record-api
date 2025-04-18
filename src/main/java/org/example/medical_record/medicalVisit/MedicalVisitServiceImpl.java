package org.example.medical_record.medicalVisit;


import org.example.medical_record.diagnose.Diagnose;
import org.example.medical_record.diagnose.DiagnoseService;
import org.example.medical_record.doctor.Doctor;
import org.example.medical_record.doctor.DoctorRepository;
import org.example.medical_record.doctor.DoctorService;
import org.example.medical_record.exception.MedicalVisitNotFoundException;
import org.example.medical_record.patient.Patient;
import org.example.medical_record.patient.PatientService;
import org.example.medical_record.sickLeave.SickLeave;
import org.example.medical_record.sickLeave.SickLeaveService;
import org.example.medical_record.web.dto.*;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class MedicalVisitServiceImpl implements MedicalVisitService {

    private final MedicalVisitRepository medicalVisitRepository;
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final SickLeaveService sickleaveService;
    private final DiagnoseService diagnoseService;

    @Autowired
    public MedicalVisitServiceImpl(MedicalVisitRepository medicalVisitRepository,
                                   DoctorService doctorService,
                                   PatientService patientService,
                                   SickLeaveService sickleaveService,
                                   DiagnoseService diagnoseService) {
        this.medicalVisitRepository = medicalVisitRepository;
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.sickleaveService = sickleaveService;
        this.diagnoseService = diagnoseService;
    }


    @Override
    public MedicalVisitExpose createVisit(MedicalVisitCreationRequest request) {

        Doctor doctor = doctorService.getDoctorById(request.getDoctorId());
        Patient patient = patientService.getPatientById(request.getPatientId());


        MedicalVisit medicalVisit = MedicalVisit.builder()
                .visitationDate(request.getDateOfVisitation())
                .doctor(doctor)
                .patient(patient)
                .diagnoses(new HashSet<>())
                .build();

        medicalVisitRepository.save(medicalVisit);

        MedicalVisitExpose medicalVisitExpose = new MedicalVisitExpose();
        medicalVisitExpose.setVisitationDate(medicalVisit.getVisitationDate());
        medicalVisitExpose.setDoctorFirstName(doctor.getFirstName());
        medicalVisitExpose.setPatientFirstName(patient.getFirstName());
        medicalVisitExpose.setPatientLastName(patient.getLastName());



        return medicalVisitExpose;
    }

    @Override
    public List<MedicalVisitExpose> getAllVisits() {

        List<MedicalVisit> allVisits = medicalVisitRepository.findAll();

        return allVisits.stream().map(MedicalVisitMapper::mapVisitToExpose).toList();

    }

    @Override
    public MedicalVisitExpose getVisitById(Long id) {

        Optional<MedicalVisit> visitOptional = medicalVisitRepository.findById(id);
        if (visitOptional.isEmpty()) {
            throw new MedicalVisitNotFoundException("There is no such visit with id: " + id);
        }

        MedicalVisit visit = visitOptional.get();

        return MedicalVisitMapper.mapVisitToExpose(visit);
    }

    @Transactional
    @Override
    public MedicalVisitExpose updateMedicalVisit(Long id, MedicalVisitEditRequest request) {

        Optional<MedicalVisit> visitOptional = medicalVisitRepository.findById(id);
        if (visitOptional.isEmpty()) {
            throw new MedicalVisitNotFoundException("There is no such visit with id: " + id);
        }

        MedicalVisit visit = visitOptional.get();

        boolean needSickLeave = request.isNeedSickLeave();
        String treatment = request.getTreatment();


        visit.setTreatment(treatment);

        if(!needSickLeave){

            medicalVisitRepository.save(visit);
            return MedicalVisitMapper.mapVisitToExpose(visit);
        }

        int daysForSickness = request.getDays();
        LocalDate  startOfTheLeave = request.getStartDate();
        String reasonForSickLeave = request.getReasonForSickLeave();


        SickLeaveRequest sickLeaveRequest = new SickLeaveRequest(daysForSickness, reasonForSickLeave, startOfTheLeave);
        SickLeave sickLeave = sickleaveService.initializeSickLeave(sickLeaveRequest);
        visit.setSickLeave(sickLeave);

        String diagnoseName = request.getNameOfTheDiagnose();
        Diagnose diagnose = diagnoseService.getDiagnoseByName(diagnoseName);

        //Avoiding LazyInitialization Exception without Fetch type eager
        Hibernate.initialize(visit.getDiagnoses());
        visit.getDiagnoses().add(diagnose);

        medicalVisitRepository.save(visit);




        return MedicalVisitMapper.mapVisitToExpose(visit);
    }

    @Override
    public MedicalVisitExpose deleteVisitById(Long id) {

        Optional<MedicalVisit> visitOptional = medicalVisitRepository.findById(id);
        if (visitOptional.isEmpty()) {
            throw new MedicalVisitNotFoundException("There is no such visit with id: " + id);
        }

        MedicalVisit visit = visitOptional.get();
        medicalVisitRepository.delete(visit);



        return MedicalVisitMapper.mapVisitToExpose(visit);
    }

    @Override
    public List<MedicalVisitExpose> getHistoryOfPatientByHisUsername(String username) {
        Patient patient = patientService.getPatientByUsername(username);

        List<MedicalVisit> medicalVisitsByPatient = medicalVisitRepository.findMedicalVisitsByPatient(patient);

        return medicalVisitsByPatient.stream().map(MedicalVisitMapper::mapVisitToExpose).toList();
    }

    @Override
    public List<MedicalVisitExpose> getVisitHistoryForPeriod(VisitHistoryPeriodRequest request) {

        LocalDate start = request.getStartDate();
        LocalDate end = request.getEndDate();

        List<MedicalVisit> visitsWithinPeriod = medicalVisitRepository.findVisitsWithinPeriod(start, end);

        return visitsWithinPeriod.stream().map(MedicalVisitMapper::mapVisitToExpose).toList();
    }

    @Override
    public List<DoctorsSickLeaves> getDoctorsWithSickLeaves() {

        List<Object[]> doctorsWithMostSickLeaves = medicalVisitRepository.findDoctorsWithMostSickLeaves();
        List<DoctorsSickLeaves> exposeList = new ArrayList<>();

        for (Object[] doctorEntity : doctorsWithMostSickLeaves) {

            Doctor doctor = (Doctor) doctorEntity[0];

            String firstName = doctor.getFirstName();
            String lastName = doctor.getLastName();

            long countOfSickLeaves = (long) doctorEntity[1];

            DoctorsSickLeaves dto = new DoctorsSickLeaves(firstName, lastName, countOfSickLeaves);

            exposeList.add(dto);


        }

        return exposeList;
    }
}
