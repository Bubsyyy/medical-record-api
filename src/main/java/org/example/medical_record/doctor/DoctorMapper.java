package org.example.medical_record.doctor;

import lombok.experimental.UtilityClass;
import org.example.medical_record.speciality.Speciality;
import org.example.medical_record.user.Role;
import org.example.medical_record.web.dto.DoctorExpose;
import org.example.medical_record.web.dto.DoctorRequest;
import org.example.medical_record.web.dto.SpecialityExpose;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

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
