package org.example.medical_record.web.controllers;

import jakarta.validation.Valid;
import org.example.medical_record.generalPractitioner.GeneralPractitionerService;
import org.example.medical_record.web.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/general-practitioners")
public class GeneralPractitionerController {

    private final GeneralPractitionerService generalPractitionerService;

    @Autowired
    public GeneralPractitionerController(GeneralPractitionerService generalPractitionerService) {
        this.generalPractitionerService = generalPractitionerService;
    }

    @PostMapping
    public ResponseEntity<GpExpose> createNewGp(@RequestBody GeneralPractitionerRequest request) {

        GpExpose newGp = generalPractitionerService.createNewGp(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(newGp);
    }

    @GetMapping("/patients/count")
    public ResponseEntity<List<PatientsCountPerGp>> getPatientsCount() {

        List<PatientsCountPerGp> countList = generalPractitionerService.getPatientsCountPerGp();

        return ResponseEntity.ok(countList);
    }

}
