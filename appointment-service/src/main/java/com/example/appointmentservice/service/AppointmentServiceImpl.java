package com.example.appointmentservice.service;

import com.example.appointmentservice.datasource.Appointments;
import com.example.appointmentservice.model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService{
    private final Appointments appointments;

    @Autowired
    public AppointmentServiceImpl(Appointments appointments) {
        this.appointments = appointments;
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointments.getAll();
    }

    @Override
    public Appointment createAppointment(Appointment appointment) {
        return appointments.save(appointment);
    }

    @Override
    public Appointment updateAppointment(Appointment appointment) {
        return appointments.update(appointment);
    }

    @Override
    public boolean deleteAppointment(Long id) {
        return appointments.delete(id);
    }
}
