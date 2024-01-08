package com.example.TrabajoGrapghQL.controllers;

import com.example.TrabajoGrapghQL.domain.Autor;
import com.example.TrabajoGrapghQL.domain.Libro;
import com.example.TrabajoGrapghQL.repositories.AutorRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class AutorController {
    private final AutorRepository autorRepository;

    public AutorController(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @QueryMapping
    Iterable<Autor> listarAutores() {
        return autorRepository.findAll();
    }

    @MutationMapping
    Autor agregarAutor(@Argument String nombre) {
        Autor autor = new Autor();
        autor.setNombre(nombre);
        return autorRepository.save(autor);
    }

    @MutationMapping
    Autor editarAutor(@Argument Long id, @Argument String nombre) {
        Autor autor = autorRepository.findById(id).orElseThrow(() -> new RuntimeException("Autor no encontrado"));
        if (nombre != null) autor.setNombre(nombre);
        return autorRepository.save(autor);
    }

    @MutationMapping
    Boolean eliminarAutor(@Argument Long id) {
        if (autorRepository.existsById(id)) {
            autorRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }


}
