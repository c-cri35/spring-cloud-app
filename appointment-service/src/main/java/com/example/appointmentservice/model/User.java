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
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String type;
}
