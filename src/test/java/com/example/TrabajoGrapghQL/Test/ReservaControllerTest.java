package com.example.TrabajoGrapghQL.Test;

import com.example.TrabajoGrapghQL.controllers.ReservaController;
import com.example.TrabajoGrapghQL.domain.Reserva;
import com.example.TrabajoGrapghQL.repositories.LibroRepository;
import com.example.TrabajoGrapghQL.repositories.ReservaRepository;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReservaControllerTest {

    @Test
    public void listarReservas() {
        ReservaRepository reservaRepository = mock(ReservaRepository.class);
        LibroRepository libroRepository = mock(LibroRepository.class);

        ReservaController reservaController = new ReservaController(reservaRepository, libroRepository);
        List<Reserva> reservas = Arrays.asList(new Reserva(), new Reserva());
        when(reservaRepository.findAll()).thenReturn(reservas);

        Iterable<Reserva> result = reservaController.listarReservas();
        assertEquals(reservas, result);
    }

    @Test
    public void buscarReservaPorLibro() {
        ReservaRepository reservaRepository = mock(ReservaRepository.class);
        LibroRepository libroRepository = mock(LibroRepository.class);

        ReservaController reservaController = new ReservaController(reservaRepository, libroRepository);
        List<Reserva> reservas = Arrays.asList(new Reserva(), new Reserva());
        when(reservaRepository.findByLibro(any())).thenReturn(reservas);

        Iterable<Reserva> result = reservaController.buscarReservaPorLibro(1L);
        assertEquals(reservas, result);
    }

    @Test
    public void agregarReserva() {
        ReservaRepository reservaRepository = mock(ReservaRepository.class);
        LibroRepository libroRepository = mock(LibroRepository.class);

        ReservaController reservaController = new ReservaController(reservaRepository, libroRepository);
        Reserva reserva = new Reserva();
        when(reservaRepository.save(any())).thenReturn(reserva);

        Reserva result = reservaController.agregarReserva(1L);
        assertEquals(reserva, result);
    }

    @Test
    public void editarReserva() {
        ReservaRepository reservaRepository = mock(ReservaRepository.class);
        LibroRepository libroRepository = mock(LibroRepository.class);

        ReservaController reservaController = new ReservaController(reservaRepository, libroRepository);
        Reserva reserva = new Reserva();
        when(reservaRepository.findById(any())).thenReturn(java.util.Optional.of(reserva));
        when(reservaRepository.save(any())).thenReturn(reserva);

        Reserva result = reservaController.editarReserva(1L, 1L);
        assertEquals(reserva, result);
    }

    @Test
    public void eliminarReserva() {
        ReservaRepository reservaRepository = mock(ReservaRepository.class);
        LibroRepository libroRepository = mock(LibroRepository.class);

        ReservaController reservaController = new ReservaController(reservaRepository, libroRepository);
        when(reservaRepository.existsById(any())).thenReturn(true);

        Boolean result = reservaController.eliminarReserva(1L);
        assertEquals(true, result);
    }
}
