package com.example.Java.Assignment.Controller;

import com.example.Java.Assignment.Models.Doctor;
import com.example.Java.Assignment.Models.Patient;
import com.example.Java.Assignment.Service.Doctor_Service;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/doctor")
public class Doctor_Controller {
    @Autowired
    private Doctor_Service doctorService;

    @PostMapping("/add_doctor")
    public ResponseEntity add_doctor(@RequestBody Doctor doctor){
        try{
            String result = doctorService.add_doctor(doctor);
            return new ResponseEntity(result, HttpStatus.OK);
        } catch(Exception e){
            log.error("doctor could not be added to the db ",e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete_doctor")
    public ResponseEntity delete_doctor(@RequestParam("Id") Integer docId){
        doctorService.delete_doctor(docId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/suggest")
    public ResponseEntity<List<Doctor>> suggestDoctors(@RequestParam("Id") Integer patId) {
        List<Doctor> suggestedDoctors = doctorService.suggest(patId);
        return new ResponseEntity<>(suggestedDoctors, HttpStatus.OK);
    }
}
