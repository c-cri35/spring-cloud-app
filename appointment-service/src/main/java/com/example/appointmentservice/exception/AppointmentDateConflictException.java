package com.example.appointmentservice.exception;

public class AppointmentDateConflictException extends RuntimeException{
    public AppointmentDateConflictException(){
        super("There is already an appointment on this day.");
    }
}
