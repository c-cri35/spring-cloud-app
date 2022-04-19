package com.example.appointmentservice.datasource;

import com.example.appointmentservice.exception.AppointmentDateConflictException;
import com.example.appointmentservice.exception.NotFoundException;
import com.example.appointmentservice.model.Appointment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class Appointments {
    private final List<Appointment> appointments = new ArrayList<>();

    public Optional<Appointment> getById(Long id) {
        return appointments.stream()
                .filter(appointment -> appointment.getId().equals(id))
                .findFirst();
    }

    public List<Appointment> getAll() {
        return new ArrayList<>(appointments);
    }

    public Appointment save(Appointment appointment) {
        if(appointments.stream()
                .anyMatch(app -> app.getStartDate().isEqual(appointment.getStartDate()))){
            throw new AppointmentDateConflictException();
        }

        appointments.add(appointment);
        return appointment;
    }

    public Appointment update(Appointment appointment) {
        Appointment existingAppointment = getById(appointment.getId()).orElseThrow(
                () -> new NotFoundException(Appointment.class, appointment.getId())
        );

        existingAppointment.setCar(appointment.getCar());
        existingAppointment.setCarOwner(appointment.getCarOwner());
        existingAppointment.setStartDate(appointment.getStartDate());
        existingAppointment.setEndDate(appointment.getEndDate());

        return appointment;
    }

    public boolean delete(Long id){
        Appointment appointment = getById(id).orElseThrow(
                () -> new NotFoundException(Appointment.class, id)
        );

        return appointments.remove(appointment);
    }
}
