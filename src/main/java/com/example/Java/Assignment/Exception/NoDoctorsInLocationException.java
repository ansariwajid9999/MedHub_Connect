package com.example.Java.Assignment.Exception;

public class NoDoctorsInLocationException extends RuntimeException {
    public NoDoctorsInLocationException(String message) {
        super(message);
    }
}

