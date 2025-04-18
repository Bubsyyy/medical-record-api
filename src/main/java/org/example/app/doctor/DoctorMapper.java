package org.example.app.doctor;

import lombok.experimental.UtilityClass;
import org.example.app.user.Role;
import org.example.app.web.dto.DoctorExpose;
import org.example.app.web.dto.DoctorRequest;
import org.example.app.web.dto.SpecialityExpose;

import java.util.HashSet;
import java.util.List;

@UtilityClass
public class DoctorMapper {

    public static DoctorExpose mapDoctorToExpose(Doctor doctor) {

        DoctorExpose doctorExpose = DoctorExpose.builder()
                .firstName(doctor.getFirstName())
                .lastName(doctor.getLastName()).build();



        List<SpecialityExpose> specialities = doctor.getSpecialities().stream()
                .map(speciality -> new SpecialityExpose(speciality.getName())).toList();

        doctorExpose.setSpecialties(specialities);
        return doctorExpose;

    }


    public static Doctor mapDtoToDoctor(DoctorRequest request) {

        Doctor doctor = new Doctor();
        doctor.setFirstName(request.getFirstName());
        doctor.setLastName(request.getLastName());
        doctor.setPersonalNumber(request.getPersonalNumber());
        doctor.setRegistrationNumber(request.getRegistrationNumber());
        doctor.setUsername(request.getUsername());
        doctor.setEmail(request.getEmail());
        doctor.setRole(Role.DOCTOR);
        doctor.setSpecialities(new HashSet<>());
        doctor.setMedicalVisits(new HashSet<>());

        return doctor;
    }
}
