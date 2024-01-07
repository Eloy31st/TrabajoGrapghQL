package com.example.TrabajoGrapghQL.controllers;

import com.example.TrabajoGrapghQL.domain.Categoria;
import com.example.TrabajoGrapghQL.domain.Libro;
import com.example.TrabajoGrapghQL.repositories.CategoriaRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @QueryMapping
    Iterable<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    @MutationMapping
    Categoria agregarCategoria(@Argument String nombre) {
        Categoria categoria = new Categoria();
        categoria.setNombre(nombre);
        return categoriaRepository.save(categoria);
    }

    @MutationMapping
    Categoria editarCategoria(@Argument Long id, @Argument String nombre) {
        Categoria categoria = categoriaRepository.findById(id).orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
        if (nombre != null) categoria.setNombre(nombre);
        return categoriaRepository.save(categoria);
    }

    @MutationMapping
    Boolean eliminarCategoria(@Argument Long id) {
        if (categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}