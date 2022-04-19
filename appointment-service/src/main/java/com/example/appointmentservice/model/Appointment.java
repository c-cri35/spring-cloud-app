package com.example.appointmentservice.model;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Data
@ToString
@EqualsAndHashCode
public class Appointment {
    private Long id;
    private Car car;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private User carOwner;
}
