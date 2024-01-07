package com.example.TrabajoGrapghQL.controllers;

import com.example.TrabajoGrapghQL.domain.Libro;
import com.example.TrabajoGrapghQL.domain.Reserva;
import com.example.TrabajoGrapghQL.repositories.LibroRepository;
import com.example.TrabajoGrapghQL.repositories.ReservaRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
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
    Iterable<Reserva> buscarReservaPorLibro(@Argument Long libroID) {
        Libro libro = libroRepository.findById(libroID).orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        return reservaRepository.findByLibro(libro);
    }

    @QueryMapping
    Iterable<Reserva> listarReservas() {
        return reservaRepository.findAll();
    }

    @MutationMapping
    Reserva agregarReserva(@Argument Long libroID) {
        Reserva reserva = new Reserva();
        reserva.setLibro(libroRepository.findById(libroID).orElseThrow(() -> new RuntimeException("Libro no encontrado")));
        return reservaRepository.save(reserva);
    }

    @MutationMapping
    Boolean eliminarReserva(@Argument Long id) {
        if (reservaRepository.existsById(id)) {
            reservaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
