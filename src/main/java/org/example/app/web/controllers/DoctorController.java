package org.example.app.web.controllers;


import jakarta.validation.Valid;
import org.example.app.doctor.DoctorService;
import org.example.app.web.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }



    @GetMapping
    public ResponseEntity<List<DoctorExpose>> getAllDoctors() {

        List<DoctorExpose> allDoctors = doctorService.getAllDoctors();

        return ResponseEntity.ok(allDoctors);
    }



    @GetMapping("/{id}")
    public ResponseEntity<DoctorExpose> getDoctor(@PathVariable Long id) {

        DoctorExpose doctorExpose = doctorService.getDoctor(id);

        return ResponseEntity.ok(doctorExpose);
    }


    @PostMapping
    public ResponseEntity<DoctorExpose> registerNewDoctor(@RequestBody @Valid DoctorRequest request) {

        DoctorExpose newDoctor = doctorService.createNewDoctor(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(newDoctor);
    }




    @PutMapping("/{id}")
    public ResponseEntity<DoctorExpose> updateDoctor(@PathVariable Long id, @RequestBody @Valid DoctorEditRequest request) {


        DoctorExpose doctorExpose = doctorService.updateDoctorById(id, request);

        return ResponseEntity.ok(doctorExpose);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DoctorExpose> deleteDoctor(@PathVariable Long id) {


        DoctorExpose doctorExpose = doctorService.deleteDoctorById(id);

        return ResponseEntity.ok(doctorExpose);
    }


    @GetMapping("visits/count")
    public ResponseEntity<List<DoctorVisitsCount>> getAllDoctorVisitsCount() {

        List<DoctorVisitsCount> doctorVisitsCounts = doctorService.getCountOfAllDoctorVisits();

        return ResponseEntity.ok(doctorVisitsCounts);
    }





    /*
    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {



        //tsest
            // Extract the username from the JWT using SecurityContextHolder
            String username = getUsernameFromSecurityContext();

            if (username == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }

            System.out.println("Username from JWT: " + username); // You can log the username for debugging

            // Now, you can use the username to fetch the doctor's data if required

            System.out.println("Get docs");
            return null;






    }


    private String getUsernameFromSecurityContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        return ((UserDetails) authentication.getPrincipal()).getUsername();



    }

     */






}
