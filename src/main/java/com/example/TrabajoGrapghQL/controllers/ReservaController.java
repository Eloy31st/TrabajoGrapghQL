package com.example.TrabajoGrapghQL.controllers;

import com.example.TrabajoGrapghQL.domain.Libro;
import com.example.TrabajoGrapghQL.domain.Reserva;
import com.example.TrabajoGrapghQL.repositories.LibroRepository;
import com.example.TrabajoGrapghQL.repositories.ReservaRepository;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class ReservaController {
    private ReservaRepository reservaRepository;
    private LibroRepository libroRepository;

    public ReservaController(ReservaRepository reservaRepository, LibroRepository libroRepository) {
        this.reservaRepository = reservaRepository;
        this.libroRepository = libroRepository;
    }

    @QueryMapping
    Iterable<Reserva> listarReservas() {
        return reservaRepository.findAll();
    }
}
