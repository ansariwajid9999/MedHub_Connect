package com.example.Java.Assignment.Models;

import com.example.Java.Assignment.Enums.City;
import com.example.Java.Assignment.Enums.Speciality;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer docId;

    private String DocName;

    @Enumerated(value = EnumType.STRING)
    private City city;

    @Email(message = "Please provide a valid email address")
    private String email;

    private String phoneNumber;

    @Enumerated(value = EnumType.STRING)
    private Speciality speciality;

}
