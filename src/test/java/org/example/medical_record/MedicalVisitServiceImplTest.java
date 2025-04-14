package org.example.medical_record;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.example.medical_record.diagnose.Diagnose;
import org.example.medical_record.diagnose.DiagnoseService;
import org.example.medical_record.doctor.Doctor;
import org.example.medical_record.doctor.DoctorService;
import org.example.medical_record.exception.MedicalVisitNotFoundException;
import org.example.medical_record.medicalVisit.MedicalVisit;
import org.example.medical_record.medicalVisit.MedicalVisitRepository;
import org.example.medical_record.medicalVisit.MedicalVisitServiceImpl;
import org.example.medical_record.patient.Patient;
import org.example.medical_record.patient.PatientService;
import org.example.medical_record.sickLeave.SickLeave;
import org.example.medical_record.sickLeave.SickLeaveService;
import org.example.medical_record.web.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class MedicalVisitServiceImplTest {

    @Mock
    private MedicalVisitRepository medicalVisitRepository;

    @Mock
    private DoctorService doctorService;

    @Mock
    private PatientService patientService;

    @Mock
    private SickLeaveService sickLeaveService;

    @Mock
    private DiagnoseService diagnoseService;

    @InjectMocks
    private MedicalVisitServiceImpl medicalVisitService;

    private Doctor doctor;
    private Patient patient;
    private MedicalVisit medicalVisit;

    @BeforeEach
    void setUp() {
        doctor = new Doctor();
        doctor.setId(1L);
        doctor.setFirstName("Lyuboslav");

        patient = new Patient();
        patient.setId(1L);
        patient.setFirstName("Lyubomir");
        patient.setLastName("Lyubomirov");

        medicalVisit = MedicalVisit.builder()
                .visitationDate(LocalDate.now())
                .doctor(doctor)
                .patient(patient)
                .diagnoses(new HashSet<>())
                .build();

        medicalVisit.setId(1L);
    }

    @Test
    void createVisit_ShouldReturnMedicalVisitExpose() {
        MedicalVisitCreationRequest request = new MedicalVisitCreationRequest(1L, 1L, LocalDate.now());
        when(doctorService.getDoctorById(1L)).thenReturn(doctor);
        when(patientService.getPatientById(1L)).thenReturn(patient);
        when(medicalVisitRepository.save(any(MedicalVisit.class))).thenReturn(medicalVisit);

        MedicalVisitExpose result = medicalVisitService.createVisit(request);

        assertNotNull(result);
        assertEquals("Lyuboslav", result.getDoctorFirstName());
        assertEquals("Lyubomir", result.getPatientFirstName());
    }

    @Test
    void getVisitById_ShouldReturnMedicalVisitExpose() {
        when(medicalVisitRepository.findById(1L)).thenReturn(Optional.of(medicalVisit));

        MedicalVisitExpose result = medicalVisitService.getVisitById(1L);

        assertNotNull(result);
        assertEquals("Lyubomir", result.getPatientFirstName());
    }

    @Test
    void getVisitById_ShouldThrowException_WhenVisitNotFound() {
        when(medicalVisitRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(MedicalVisitNotFoundException.class, () -> medicalVisitService.getVisitById(1L));
    }

    @Test
    void deleteVisitById_ShouldReturnMedicalVisitExpose() {
        when(medicalVisitRepository.findById(1L)).thenReturn(Optional.of(medicalVisit));

        MedicalVisitExpose result = medicalVisitService.deleteVisitById(1L);

        assertNotNull(result);
        verify(medicalVisitRepository, times(1)).delete(medicalVisit);
    }


    @Test
    void getHistoryOfPatientByHisUsername_ShouldReturnMedicalVisits() {
        when(patientService.getPatientByUsername("lyubo"))
                .thenReturn(patient);
        when(medicalVisitRepository.findMedicalVisitsByPatient(patient))
                .thenReturn(List.of(medicalVisit));

        List<MedicalVisitExpose> result = medicalVisitService.getHistoryOfPatientByHisUsername("lyubo");

        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    void getVisitHistoryForPeriod_ShouldReturnVisits() {
        VisitHistoryPeriodRequest request = new VisitHistoryPeriodRequest(LocalDate.now().minusDays(10), LocalDate.now());
        when(medicalVisitRepository.findVisitsWithinPeriod(request.getStartDate(), request.getEndDate()))
                .thenReturn(List.of(medicalVisit));

        List<MedicalVisitExpose> result = medicalVisitService.getVisitHistoryForPeriod(request);

        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    void updateMedicalVisit_ShouldUpdateVisitAndReturnExpose() {
        MedicalVisitEditRequest request = new MedicalVisitEditRequest("Flu", "Flu Treatment", true, "Flu reason", 5, LocalDate.now());
        Diagnose diagnose = new Diagnose();
        diagnose.setName("Flu");
        SickLeave sickLeave = new SickLeave();
        when(medicalVisitRepository.findById(1L)).thenReturn(Optional.of(medicalVisit));
        when(diagnoseService.getDiagnoseByName("Flu")).thenReturn(diagnose);
        when(sickLeaveService.initializeSickLeave(any(SickLeaveRequest.class))).thenReturn(sickLeave);
        when(medicalVisitRepository.save(any(MedicalVisit.class))).thenReturn(medicalVisit);

        MedicalVisitExpose result = medicalVisitService.updateMedicalVisit(1L, request);

        assertNotNull(result);
        verify(medicalVisitRepository, times(1)).save(medicalVisit);
    }

    @Test
    void getDoctorsWithSickLeaves_ShouldReturnDoctorsList() {
        Object[] doctorEntity1 = new Object[]{doctor, 5L};
        Doctor doctor2 = new Doctor();
        doctor2.setFirstName("Denislav");
        Object[] doctorEntity2 = new Object[]{doctor2, 3L};

        when(medicalVisitRepository.findDoctorsWithMostSickLeaves())
                .thenReturn(List.of(doctorEntity1, doctorEntity2));

        List<DoctorsSickLeaves> result = medicalVisitService.getDoctorsWithSickLeaves();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Lyuboslav", result.get(0).getFirstName());
        assertEquals("Denislav", result.get(1).getFirstName());

    }




}

