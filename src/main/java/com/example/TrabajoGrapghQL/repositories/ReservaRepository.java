package com.example.TrabajoGrapghQL.repositories;

import com.example.TrabajoGrapghQL.domain.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}
