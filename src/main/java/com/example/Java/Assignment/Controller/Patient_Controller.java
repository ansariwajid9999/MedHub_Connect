package com.example.Java.Assignment.Controller;

import com.example.Java.Assignment.Models.Patient;
import com.example.Java.Assignment.Service.Patient_Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/patient")
public class Patient_Controller {
    @Autowired
    private Patient_Service patientService;

    @PostMapping("/add_patient")
    public ResponseEntity add_Patient(@RequestBody Patient patient){
        try{
            String result = patientService.add_Patient(patient);
            return new ResponseEntity(result, HttpStatus.OK);
        } catch(Exception e){
            log.error("patient could not be added to the db ",e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete_patient")
    public ResponseEntity delete_patient(@RequestParam("Id") Integer patId){
        patientService.delete_patient(patId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
