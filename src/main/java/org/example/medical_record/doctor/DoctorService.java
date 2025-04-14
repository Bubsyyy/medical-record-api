package org.example.medical_record.doctor;

import jakarta.validation.Valid;
import org.example.medical_record.web.dto.DoctorEditRequest;
import org.example.medical_record.web.dto.DoctorExpose;
import org.example.medical_record.web.dto.DoctorRequest;
import org.example.medical_record.web.dto.DoctorVisitsCount;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface DoctorService {
    List<DoctorExpose> getAllDoctors();

    DoctorExpose getDoctor(Long id);

    DoctorExpose createNewDoctor(DoctorRequest request);

    DoctorExpose updateDoctorById(Long id,DoctorEditRequest request);


    DoctorExpose deleteDoctorById(Long id);

    Doctor getDoctorByNames(String firstName, String lastName);

    Doctor getDoctorById(Long doctorId);

    List<DoctorVisitsCount> getCountOfAllDoctorVisits();

}
