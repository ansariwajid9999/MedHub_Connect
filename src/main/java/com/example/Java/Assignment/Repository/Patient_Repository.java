package com.example.Java.Assignment.Repository;

import com.example.Java.Assignment.Models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Patient_Repository extends JpaRepository<Patient , Integer> {

}
