package com.example.TrabajoGrapghQL.Test;

import com.example.TrabajoGrapghQL.controllers.CategoriaController;
import com.example.TrabajoGrapghQL.domain.Categoria;
import com.example.TrabajoGrapghQL.repositories.CategoriaRepository;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CategoriaControllerTest {

    @Test
    public void listarCategorias() {
        CategoriaRepository categoriaRepository = mock(CategoriaRepository.class);

        CategoriaController categoriaController = new CategoriaController(categoriaRepository);
        List<Categoria> categorias = Arrays.asList(new Categoria(), new Categoria());
        when(categoriaRepository.findAll()).thenReturn(categorias);

        Iterable<Categoria> result = categoriaController.listarCategorias();
        assertEquals(categorias, result);
    }

    @Test
    public void agregarCategoria() {
        CategoriaRepository categoriaRepository = mock(CategoriaRepository.class);

        CategoriaController categoriaController = new CategoriaController(categoriaRepository);
        Categoria categoria = new Categoria();
        categoria.setNombre("nombre");
        when(categoriaRepository.save(any())).thenReturn(categoria);

        Categoria result = categoriaController.agregarCategoria("nombre");
        assertEquals(categoria, result);
    }

    @Test
    public void editarCategoria() {
        CategoriaRepository categoriaRepository = mock(CategoriaRepository.class);

        CategoriaController categoriaController = new CategoriaController(categoriaRepository);
        Categoria categoria = new Categoria();
        categoria.setNombre("nombre");
        when(categoriaRepository.findById(any())).thenReturn(java.util.Optional.of(categoria));
        when(categoriaRepository.save(any())).thenReturn(categoria);

        Categoria result = categoriaController.editarCategoria(1L, "nombre");
        assertEquals(categoria, result);
    }

    @Test
    public void eliminarCategoria() {
        CategoriaRepository categoriaRepository = mock(CategoriaRepository.class);

        CategoriaController categoriaController = new CategoriaController(categoriaRepository);
        Categoria categoria = new Categoria();
        categoria.setNombre("nombre");
        when(categoriaRepository.existsById(any())).thenReturn(true);

        Boolean result = categoriaController.eliminarCategoria(1L);
        assertEquals(true, result);
    }
}
