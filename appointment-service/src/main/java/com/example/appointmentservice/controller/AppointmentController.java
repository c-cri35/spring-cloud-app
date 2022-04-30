package com.example.appointmentservice.controller;

import com.example.appointmentservice.exception.AppointmentDateConflictException;
import com.example.appointmentservice.exception.NotFoundException;
import com.example.appointmentservice.model.Appointment;
import com.example.appointmentservice.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
@RefreshScope
public class AppointmentController {
    @Value("${allow-unregistered-users: false}")
    private Boolean allowUnregisteredUsers;

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    /**
     * Creates an appointment with given details.
     * @return created appointment
     * @throws HttpStatus.CONFLICT if there is already an appointment scheduled for the same day
     *         HttpStatus.NOT_FOUND if the car owner is not registered by user-service
     *         and the flag allow-unregistered users is enabled
     */
    @PostMapping
    public ResponseEntity<?> createAppointment(@RequestBody Appointment appointment) {
        try {
            return ResponseEntity.ok(appointmentService.createAppointment(appointment, allowUnregisteredUsers));
        } catch (AppointmentDateConflictException ex) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(ex.getMessage());
        } catch (NotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ex.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> updateAppointment(@RequestBody Appointment appointment) {
        try {
            return ResponseEntity.ok(appointmentService.updateAppointment(appointment));
        } catch (NotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(appointmentService.deleteAppointment(id));
        } catch (NotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ex.getMessage());
        }
    }
}
