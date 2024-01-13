package com.example.Java.Assignment.Models;

import com.example.Java.Assignment.Enums.Symptom;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer patId;

    private String patientName;

    @Email(message = "Please provide a valid email address")
    private String email;

    private String phoneNumber;

    private String city;

    @Enumerated(value = EnumType.STRING)
    private Symptom symptom;
}
