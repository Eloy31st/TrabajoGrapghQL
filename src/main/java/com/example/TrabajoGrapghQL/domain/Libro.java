package com.example.TrabajoGrapghQL.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @ManyToOne
    private Autor autor;
    @ManyToOne
    private Categoria categoria;
}
