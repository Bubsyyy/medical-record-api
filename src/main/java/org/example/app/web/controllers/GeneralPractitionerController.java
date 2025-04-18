package org.example.app.web.controllers;

import org.example.app.generalPractitioner.GeneralPractitionerService;
import org.example.app.web.dto.*;
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
