package com.example.TrabajoGrapghQL.controllers;

import com.example.TrabajoGrapghQL.domain.Autor;
import com.example.TrabajoGrapghQL.domain.Categoria;
import com.example.TrabajoGrapghQL.domain.Libro;
import com.example.TrabajoGrapghQL.repositories.AutorRepository;
import com.example.TrabajoGrapghQL.repositories.CategoriaRepository;
import com.example.TrabajoGrapghQL.repositories.LibroRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class LibroController {

    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;
    private final CategoriaRepository categoriaRepository;

    public LibroController(LibroRepository libroRepository, AutorRepository autorRepository, CategoriaRepository categoriaRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @QueryMapping
    Iterable<Libro> listarLibros() {
        return libroRepository.findAll();
    }

    @QueryMapping
    Iterable<Libro> buscarLibrosPorTitulo(@Argument String titulo) {
        return libroRepository.findByTitulo(titulo);
    }

    @QueryMapping
    Iterable<Libro> buscarLibrosPorAutor(@Argument Long autorId) {
        Autor autor = autorRepository.findById(autorId).orElseThrow(() -> new RuntimeException("Autor no encontrado"));
        return libroRepository.findByAutor(autor);
    }

    @QueryMapping
    Iterable<Libro> buscarLibrosPorCategoria(@Argument Long categoriaId) {
        Categoria categoria = categoriaRepository.findById(categoriaId).orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
        return libroRepository.findByCategoria(categoria);
    }


    @MutationMapping
    Boolean eliminarLibro(@Argument Long id) {
        if (libroRepository.existsById(id)) {
            libroRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
