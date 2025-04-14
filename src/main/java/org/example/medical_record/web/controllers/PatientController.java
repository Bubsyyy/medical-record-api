package org.example.medical_record.web.controllers;


import jakarta.validation.Valid;
import org.example.medical_record.patient.PatientService;
import org.example.medical_record.web.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/patients")
public class PatientController {

    private final PatientService patientService;


    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<List<PatientExpose>> getAllPatients() {

        List<PatientExpose> allPatients = patientService.getAllPatients();

        return ResponseEntity.ok(allPatients);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PatientExpose> getPatient(@PathVariable Long id) {

        PatientExpose patientExpose = patientService.getPatient(id);

        return ResponseEntity.ok(patientExpose);
    }

    @PostMapping
    public ResponseEntity<PatientExpose> registerNewPatient(@RequestBody @Valid PatientRequest request) {

        PatientExpose newPatient = patientService.createNewPatient(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(newPatient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientExpose> updateDoctorOfThePatient(@PathVariable Long id, @RequestBody @Valid PatientEditRequest request) {


        PatientExpose patientExpose = patientService.updatePatientById(id, request);

        return ResponseEntity.ok(patientExpose);
    }

    @GetMapping("/diagnoses/{id}")
    public ResponseEntity<List<PatientExpose>> getAllPatientsWithSpecificDiagnose(@PathVariable Long id) {

        List<PatientExpose> patientsWithDiagnose = patientService.getAllPatientsWithDiagnoseByDiagnoseId(id);


        return ResponseEntity.ok(patientsWithDiagnose);
    }

    @GetMapping("/general-practitioners/{id}")
    public ResponseEntity<List<PatientExpose>> getAllPatientsWithGeneralPractitioner(@PathVariable Long id) {

        List<PatientExpose> patients = patientService.getAllPatientsWithSpecificGeneralPractitioner(id);

        return ResponseEntity.ok(patients);
    }



}
