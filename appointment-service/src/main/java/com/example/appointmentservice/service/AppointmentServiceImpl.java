package com.example.appointmentservice.service;

import com.example.appointmentservice.datasource.Appointments;
import com.example.appointmentservice.exception.NotFoundException;
import com.example.appointmentservice.model.Appointment;
import com.example.appointmentservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService{
    private final Appointments appointments;
    private final RestTemplate restTemplate;
    private final CircuitBreakerFactory circuitBreakerFactory;

    private final static String USER_URL = "http://localhost:8080/user";

    @Autowired
    public AppointmentServiceImpl(Appointments appointments, RestTemplate restTemplate, CircuitBreakerFactory circuitBreakerFactory) {
        this.appointments = appointments;
        this.restTemplate = restTemplate;
        this.circuitBreakerFactory = circuitBreakerFactory;
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointments.getAll();
    }

    @Override
    public Appointment createAppointment(Appointment appointment, Boolean allowUnregisteredUsers) {
        if (allowUnregisteredUsers)
            return appointments.save(appointment);

        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("user-circuit-breaker");

        // if service returns error or takes to long to respond, interpret the result as NOT FOUND
        HttpStatus userStatusCode = circuitBreaker.run(
                () -> restTemplate.getForEntity(USER_URL + "/" + appointment.getCarOwner().getId(),
                                                User.class).getStatusCode(),
                throwable -> HttpStatus.NOT_FOUND);

        if (userStatusCode == HttpStatus.OK)
            return appointments.save(appointment);

        throw new NotFoundException(User.class, appointment.getCarOwner().getId());
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
