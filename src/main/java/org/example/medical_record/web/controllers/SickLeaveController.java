package org.example.medical_record.web.controllers;

import jakarta.validation.Valid;
import org.example.medical_record.sickLeave.SickLeaveService;
import org.example.medical_record.web.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sick-leaves")
public class SickLeaveController {

    private final SickLeaveService sickLeaveService;


    @Autowired
    public SickLeaveController(SickLeaveService sickLeaveService) {
        this.sickLeaveService = sickLeaveService;
    }


    @GetMapping
    public ResponseEntity<List<SickLeaveExpose>> getAllSickLeaves() {
        List<SickLeaveExpose> allSickLeaves = sickLeaveService.getAllSickLeaves();
        return ResponseEntity.ok(allSickLeaves);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SickLeaveExpose> getSickLeave(@PathVariable Long id) {
        SickLeaveExpose sickLeave = sickLeaveService.getSickLeave(id);
        return ResponseEntity.ok(sickLeave);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SickLeaveExpose> updateSickLeave(@PathVariable Long id, @RequestBody SickLeaveEditRequest request) {
        SickLeaveExpose sickLeaveExpose = sickLeaveService.updateSickLeave(id, request);
        return ResponseEntity.ok(sickLeaveExpose);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<SickLeaveExpose> deleteSickLeave(@PathVariable Long id) {
        SickLeaveExpose sickLeaveExpose = sickLeaveService.deleteSickLeave(id);
        return ResponseEntity.ok(sickLeaveExpose);
    }


    @GetMapping("/month/high")
    public ResponseEntity<MonthWithSickLeaves> getMonthWithHighestSickLeaves(){

        MonthWithSickLeaves month = sickLeaveService.getMonthWithMostSickLeaveCount();

        return ResponseEntity.ok(month);
    }
}
