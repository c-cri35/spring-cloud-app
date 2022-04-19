package com.example.appointmentservice.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Data
@ToString
@EqualsAndHashCode
public class Car {
    private Long id;
    private String brand;
    private String model;
}
