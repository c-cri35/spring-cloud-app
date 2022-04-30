package com.example.appointmentservice.service;

import com.example.appointmentservice.datasource.Appointments;
import com.example.appointmentservice.exception.NotFoundException;
import com.example.appointmentservice.model.Appointment;
import com.example.appointmentservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService{
    private final Appointments appointments;
    private final RestTemplate restTemplate;

    private final static String USER_URL = "http://localhost:8080/user";

    @Autowired
    public AppointmentServiceImpl(Appointments appointments, RestTemplate restTemplate) {
        this.appointments = appointments;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointments.getAll();
    }

    @Override
    public Appointment createAppointment(Appointment appointment, Boolean allowUnregisteredUsers) {
        if (allowUnregisteredUsers)
            return appointments.save(appointment);

        try {
            HttpStatus userStatus = restTemplate.getForEntity(USER_URL + "/" + appointment.getCarOwner().getId(),
                    User.class).getStatusCode();
            return appointments.save(appointment);
        } catch (RestClientException ex) {
            throw new NotFoundException(User.class, appointment.getCarOwner().getId());
        }
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
