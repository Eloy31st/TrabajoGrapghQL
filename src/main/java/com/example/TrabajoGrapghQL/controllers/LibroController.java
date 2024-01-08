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
    public Iterable<Libro> listarLibros() {
        return libroRepository.findAll();
    }

    @QueryMapping
    public Iterable<Libro> buscarLibrosPorTitulo(@Argument String titulo) {
        return libroRepository.findByTitulo(titulo);
    }

    @QueryMapping
    public Iterable<Libro> buscarLibrosPorAutor(@Argument Long autorID) {
        Autor autor = autorRepository.findById(autorID).orElseThrow(() -> new RuntimeException("Autor no encontrado"));
        return libroRepository.findByAutor(autor);
    }

    @QueryMapping
    public Iterable<Libro> buscarLibrosPorCategoria(@Argument Long categoriaID) {
        Categoria categoria = categoriaRepository.findById(categoriaID).orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
        return libroRepository.findByCategoria(categoria);
    }

    @MutationMapping
    public Libro agregarLibro(@Argument String titulo, @Argument Long autorID, @Argument Long categoriaID) {
        Libro libro = new Libro();
        libro.setTitulo(titulo);
        Autor autor = autorRepository.findById(autorID).orElseThrow(() -> new RuntimeException("Autor no encontrado"));
        Categoria categoria = categoriaRepository.findById(categoriaID).orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
        libro.setAutor(autor);
        libro.setCategoria(categoria);
        return libroRepository.save(libro);
    }

    @MutationMapping
    public Libro editarLibro(@Argument Long id, @Argument String titulo, @Argument Long autorID, @Argument Long categoriaID) {
        Libro libro = libroRepository.findById(id).orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        if (titulo != null) libro.setTitulo(titulo);
        if (autorID != null) libro.setAutor(autorRepository.findById(autorID).orElseThrow(() -> new RuntimeException("Autor no encontrado")));
        if (categoriaID != null) libro.setCategoria(categoriaRepository.findById(categoriaID).orElseThrow(() -> new RuntimeException("Categoria no encontrada")));
        return libroRepository.save(libro);
    }


    @MutationMapping
    public Boolean eliminarLibro(@Argument Long id) {
        if (libroRepository.existsById(id)) {
            libroRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
