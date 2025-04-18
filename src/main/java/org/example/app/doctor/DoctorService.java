package org.example.app.doctor;

import org.example.app.web.dto.DoctorEditRequest;
import org.example.app.web.dto.DoctorExpose;
import org.example.app.web.dto.DoctorRequest;
import org.example.app.web.dto.DoctorVisitsCount;

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
