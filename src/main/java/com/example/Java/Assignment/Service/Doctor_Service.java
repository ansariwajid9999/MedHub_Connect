package com.example.Java.Assignment.Service;

import com.example.Java.Assignment.Enums.City;
import com.example.Java.Assignment.Enums.Symptom;
import com.example.Java.Assignment.Exception.NoDoctorsForSymptomException;
import com.example.Java.Assignment.Exception.NoDoctorsInLocationException;
import com.example.Java.Assignment.Exception.PatientNotFoundException;
import com.example.Java.Assignment.Models.Doctor;
import com.example.Java.Assignment.Models.Patient;
import com.example.Java.Assignment.Repository.Doctor_Repository;
import com.example.Java.Assignment.Repository.Patient_Repository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
@Slf4j
public class Doctor_Service {
    @Autowired
    private Doctor_Repository doctorRepository;

    @Autowired
    private Patient_Repository patientRepository;

    public String add_doctor(Doctor doctor){
        String name = doctor.getDocName();
        String number = doctor.getPhoneNumber();

        boolean a = false;
        boolean b = false;

        if(name != null && name.length() >= 3) a = true;
        if(number != null && number.length() == 10) b = true;

        if(a && b){
            doctorRepository.save(doctor);
            return "doctor has been successfully to the db";
        }
        return "Validation failed";
    }

    public void delete_doctor(Integer docId){
        doctorRepository.deleteById(docId);
    }

    public List<Doctor> suggest(Integer patId) {

        Patient patient = patientRepository.findById(patId).orElse(null);

        String patientCity = patient.getCity();

        int flag = 0;
        String[] arr = {"DELHI","NOIDA","FARIDABAD"};
        for(String s : arr){
            if(s.equals(patientCity)){
                flag = 1;
            }
        }

        if (flag == 0) {
            throw new NoDoctorsInLocationException("We are still waiting to expand to your location");
        }

        if (patient != null) {
            List<Doctor> doctorsInLocation = doctorRepository.findByCity(City.valueOf(patientCity));

            log.info("Patient City: {}", patientCity);

            log.info("Doctors in Location: {}", doctorsInLocation.get(0).getCity());

            List<Doctor> suggestedDoctors = new ArrayList<>();

            for (Doctor doctor : doctorsInLocation) {
                String doctorSpeciality = String.valueOf(doctor.getSpeciality());
                String expectedSpeciality = getSpecialityBySymptom(patient.getSymptom());

                if (doctorSpeciality.equals(expectedSpeciality)) {
                    suggestedDoctors.add(doctor);
                }
            }

            if (suggestedDoctors.isEmpty()) {
                throw new NoDoctorsForSymptomException("There isn't any doctor present at your location for your symptom");
            }
            return suggestedDoctors;
        } else {
            throw new PatientNotFoundException("Patient not found with ID: " + patId);
        }
    }

    private String getSpecialityBySymptom(Symptom symptom) {
        switch (symptom) {
            case Arthritis:
            case Back_Pain:
            case Tissue_injuries:
                return "Orthopedic";
            case Dysmenorrhea:
                return "Gynecology";
            case Skin_infection:
            case skin_burn:
                return "Dermatology";
            case Ear_pain:
                return "ENT_specialist";
            default:
                return null;
        }
    }
}
