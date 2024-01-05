package com.example.TrabajoGrapghQL.repositories;

import com.example.TrabajoGrapghQL.domain.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
}
