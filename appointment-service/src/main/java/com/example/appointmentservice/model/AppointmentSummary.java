package com.example.appointmentservice.model;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Data
@ToString
@EqualsAndHashCode
public class AppointmentSummary {
    private Long id;
    private Appointment appointment;
    private User mechanic;
    private String comment;
    private BigDecimal totalCost;
}
