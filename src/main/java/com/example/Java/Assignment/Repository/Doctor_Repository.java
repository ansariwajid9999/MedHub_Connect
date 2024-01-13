package com.example.Java.Assignment.Repository;

import com.example.Java.Assignment.Enums.City;
import com.example.Java.Assignment.Models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@org.springframework.stereotype.Repository

public interface Doctor_Repository extends JpaRepository<Doctor , Integer> {
    List<Doctor> findByCity(City city);
}
