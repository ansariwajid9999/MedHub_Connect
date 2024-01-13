package com.example.Java.Assignment.Exception;

public class NoDoctorsForSymptomException extends RuntimeException {
    public NoDoctorsForSymptomException(String message) {
        super(message);
    }
}
