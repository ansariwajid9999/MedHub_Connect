package com.example.Java.Assignment.Service;

import com.example.Java.Assignment.Models.Patient;
import com.example.Java.Assignment.Repository.Patient_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Patient_Service {
    @Autowired
    private Patient_Repository patientRepository;

    public String add_Patient(Patient patient){
        String name = patient.getPatientName();
        String number = patient.getPhoneNumber();

        boolean a = false;
        boolean b = false;

        if(name != null && name.length() >= 3) a = true;
        if(number != null && number.length() == 10) b = true;


        if(a && b){
            patientRepository.save(patient);
            return "patient has been successfully to the db";
        }
        return "Validation failed";
    }

    public void delete_patient(Integer patId){
        patientRepository.deleteById(patId);
    }
}
