package org.example.medical_record.web.controllers;

import jakarta.validation.Valid;
import org.example.medical_record.medicalVisit.MedicalVisit;
import org.example.medical_record.medicalVisit.MedicalVisitService;
import org.example.medical_record.web.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medical-visits")
public class MedicalVisitController {

    private final MedicalVisitService medicalVisitService;


    @Autowired
    public MedicalVisitController(MedicalVisitService medicalVisitService) {
        this.medicalVisitService = medicalVisitService;
    }


    @GetMapping
    public ResponseEntity<List<MedicalVisitExpose>> getAllMedicalVisits() {

        List<MedicalVisitExpose> visitsExpose = medicalVisitService.getAllVisits();

        return ResponseEntity.ok(visitsExpose);
    }


    @GetMapping("/{id}")
    public ResponseEntity<MedicalVisitExpose> getMedicalVisit(@PathVariable Long id) {

        MedicalVisitExpose visitExpose = medicalVisitService.getVisitById(id);

        return ResponseEntity.ok(visitExpose);
    }


    @PutMapping("/{id}")
    public ResponseEntity<MedicalVisitExpose> updateVisit(@PathVariable Long id, @RequestBody MedicalVisitEditRequest request) {


        MedicalVisitExpose medicalVisitExpose = medicalVisitService.updateMedicalVisit(id, request);

        return ResponseEntity.ok(medicalVisitExpose);
    }



    @PostMapping
    public ResponseEntity<MedicalVisitExpose> createNewMedicalVisit(@RequestBody @Valid MedicalVisitCreationRequest request) {


        MedicalVisitExpose visitExpose = medicalVisitService.createVisit(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(visitExpose);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MedicalVisitExpose> deleteMedicalVisit(@PathVariable Long id) {

        MedicalVisitExpose deletedVisit = medicalVisitService.deleteVisitById(id);

        return ResponseEntity.ok(deletedVisit);
    }

    @GetMapping("/patients/history")
    public ResponseEntity<List<MedicalVisitExpose>> getPatientHistory() {


        //TODO get the history of the patient
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = (authentication != null && authentication.getPrincipal() instanceof UserDetails)
                ? ((UserDetails) authentication.getPrincipal()).getUsername()
                : null;

        if (username == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        List<MedicalVisitExpose> history = medicalVisitService.getHistoryOfPatientByHisUsername(username);


        return ResponseEntity.ok(history);
    }


    @GetMapping("/period")
    public ResponseEntity<List<MedicalVisitExpose>> getVisitHistoryPeriod(@RequestBody VisitHistoryPeriodRequest request) {




        List<MedicalVisitExpose> history = medicalVisitService.getVisitHistoryForPeriod(request);


        return ResponseEntity.ok(history);
    }


    @GetMapping("/doctors/most/sick-leaves")
    public ResponseEntity<List<DoctorsSickLeaves>> getDoctorsWithSickLeaves(){

        List<DoctorsSickLeaves> doctorsSickLeaves = medicalVisitService.getDoctorsWithSickLeaves();

        return ResponseEntity.ok(doctorsSickLeaves);
    }



}
