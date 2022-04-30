package com.example.appointmentservice.service;

import com.example.appointmentservice.model.Appointment;

import java.util.List;

public interface AppointmentService {
    List<Appointment> getAllAppointments();

    Appointment createAppointment(Appointment appointment, Boolean allowUnregisteredUsers);

    Appointment updateAppointment(Appointment appointment);

    boolean deleteAppointment(Long id);
}
