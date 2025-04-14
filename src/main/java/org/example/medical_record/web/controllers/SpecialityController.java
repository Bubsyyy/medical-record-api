package org.example.medical_record.web.controllers;

import jakarta.validation.Valid;
import org.example.medical_record.speciality.SpecialityService;
import org.example.medical_record.web.dto.AuthResponse;
import org.example.medical_record.web.dto.SpecialityExpose;
import org.example.medical_record.web.dto.SpecialityRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/specialities")
public class SpecialityController {

    private final SpecialityService specialityService;

    @Autowired
    public SpecialityController(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @GetMapping
    public ResponseEntity<List<SpecialityExpose>> getAllSpecialities() {

        List<SpecialityExpose> allSpecialities = specialityService.getAllSpecialities();

        return ResponseEntity.ok(allSpecialities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecialityExpose> getSpeciality(@PathVariable Long id) {

        SpecialityExpose speciality = specialityService.getSpeciality(id);

        return ResponseEntity.ok(speciality);
    }

    @PostMapping
    public ResponseEntity<SpecialityExpose> createNewSpeciality(@RequestBody @Valid SpecialityRequest request) {

        SpecialityExpose newSpeciality = specialityService.createNewSpeciality(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(newSpeciality);
    }



    @PutMapping("/{id}")
    public ResponseEntity<SpecialityExpose> updateSpeciality(@PathVariable Long id, @RequestBody @Valid SpecialityRequest request) {


        SpecialityExpose specialityExpose = specialityService.updateSpeciality(id, request);

        return ResponseEntity.ok(specialityExpose);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SpecialityExpose> deleteSpeciality(@PathVariable Long id) {


        SpecialityExpose specialityExpose = specialityService.deleteSpeciality(id);

        return ResponseEntity.ok(specialityExpose);
    }





}
