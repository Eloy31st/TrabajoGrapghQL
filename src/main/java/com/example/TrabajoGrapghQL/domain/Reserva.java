package com.example.TrabajoGrapghQL.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Libro libro;
}
