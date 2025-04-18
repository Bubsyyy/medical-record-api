package org.example.app.web.controllers;


import org.example.app.diagnose.DiagnoseService;
import org.example.app.web.dto.DiagnoseCreationRequest;
import org.example.app.web.dto.DiagnoseExpose;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/diagnoses")
public class DiagnoseController {

    private final DiagnoseService diagnoseService;


    @Autowired
    public DiagnoseController(DiagnoseService diagnoseService) {
        this.diagnoseService = diagnoseService;
    }

    @GetMapping
    public ResponseEntity<List<DiagnoseExpose>> getAllDiagnoses(){

        List<DiagnoseExpose> diagnoseExposeList = diagnoseService.getAllDiagnoses();

        return ResponseEntity.ok(diagnoseExposeList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiagnoseExpose> getDiagnoses(@PathVariable Long id){

        DiagnoseExpose diagnoseExpose = diagnoseService.getDiagnosesById(id);

        return ResponseEntity.ok(diagnoseExpose);
    }

    @PostMapping
    public ResponseEntity<DiagnoseExpose> createNewDiagnose(@RequestBody DiagnoseCreationRequest request) {

        DiagnoseExpose newDiagnose = diagnoseService.createNewDiagnose(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(newDiagnose);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DiagnoseExpose> deleteDiagnose(@PathVariable Long id) {

        DiagnoseExpose deletedDiagnose = diagnoseService.deleteDiagnoseById(id);

        return ResponseEntity.ok(deletedDiagnose);
    }

    @GetMapping("last")
    public ResponseEntity<DiagnoseExpose> getLastDiagnoses(){

        DiagnoseExpose diagnose = diagnoseService.getLastDiagnose();

        return ResponseEntity.ok(diagnose);

    }


}
