package com.example.TrabajoGrapghQL.Test;

import com.example.TrabajoGrapghQL.controllers.LibroController;
import com.example.TrabajoGrapghQL.domain.Autor;
import com.example.TrabajoGrapghQL.domain.Libro;
import com.example.TrabajoGrapghQL.repositories.AutorRepository;
import com.example.TrabajoGrapghQL.repositories.CategoriaRepository;
import com.example.TrabajoGrapghQL.repositories.LibroRepository;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LibroControllerTest {

    @Test
    public void listarLibros() {
        LibroRepository libroRepository = mock(LibroRepository.class);
        AutorRepository autorRepository = mock(AutorRepository.class);
        CategoriaRepository categoriaRepository = mock(CategoriaRepository.class);

        LibroController libroController = new LibroController(libroRepository, autorRepository, categoriaRepository);
        List<Libro> libros = Arrays.asList(new Libro(), new Libro());
        when(libroRepository.findAll()).thenReturn(libros);

        Iterable<Libro> result = libroController.listarLibros();
        assertEquals(libros, result);
    }

    @Test
    public void buscarLibrosPorTitulo() {
        LibroRepository libroRepository = mock(LibroRepository.class);
        AutorRepository autorRepository = mock(AutorRepository.class);
        CategoriaRepository categoriaRepository = mock(CategoriaRepository.class);

        LibroController libroController = new LibroController(libroRepository, autorRepository, categoriaRepository);
        List<Libro> libros = Arrays.asList(new Libro(), new Libro());
        when(libroRepository.findByTitulo("titulo")).thenReturn(libros);

        Iterable<Libro> result = libroController.buscarLibrosPorTitulo("titulo");
        assertEquals(libros, result);
    }

    @Test
    public void buscarLibrosPorAutor() {
        LibroRepository libroRepository = mock(LibroRepository.class);
        AutorRepository autorRepository = mock(AutorRepository.class);
        CategoriaRepository categoriaRepository = mock(CategoriaRepository.class);

        LibroController libroController = new LibroController(libroRepository, autorRepository, categoriaRepository);
        List<Libro> libros = Arrays.asList(new Libro(), new Libro());
        when(libroRepository.findByAutor(any())).thenReturn(libros);

        Iterable<Libro> result = libroController.buscarLibrosPorAutor(1L);
        assertEquals(libros, result);
    }

    @Test
    public void buscarLibrosPorCategoria() {
        LibroRepository libroRepository = mock(LibroRepository.class);
        AutorRepository autorRepository = mock(AutorRepository.class);
        CategoriaRepository categoriaRepository = mock(CategoriaRepository.class);

        LibroController libroController = new LibroController(libroRepository, autorRepository, categoriaRepository);
        List<Libro> libros = Arrays.asList(new Libro(), new Libro());
        when(libroRepository.findByCategoria(any())).thenReturn(libros);

        Iterable<Libro> result = libroController.buscarLibrosPorCategoria(1L);
        assertEquals(libros, result);
    }

    @Test
    public void agregarLibro() {
        LibroRepository libroRepository = mock(LibroRepository.class);
        AutorRepository autorRepository = mock(AutorRepository.class);
        CategoriaRepository categoriaRepository = mock(CategoriaRepository.class);

        LibroController libroController = new LibroController(libroRepository, autorRepository, categoriaRepository);
        Libro libro = new Libro();
        libro.setTitulo("titulo");
        Autor autor = new Autor();
        autor.setId(1L);
        libro.setAutor(autor);
        when(libroRepository.save(any())).thenReturn(libro);

        Libro result = libroController.agregarLibro("titulo", 1L, 1L);
        assertEquals(libro, result);
    }

    @Test
    public void editarLibro() {
        LibroRepository libroRepository = mock(LibroRepository.class);
        AutorRepository autorRepository = mock(AutorRepository.class);
        CategoriaRepository categoriaRepository = mock(CategoriaRepository.class);

        LibroController libroController = new LibroController(libroRepository, autorRepository, categoriaRepository);
        Libro libro = new Libro();
        libro.setTitulo("titulo");
        Autor autor = new Autor();
        autor.setId(1L);
        libro.setAutor(autor);
        when(libroRepository.save(any())).thenReturn(libro);

        Libro result = libroController.editarLibro(1L, "titulo", 1L, 1L);
        assertEquals(libro, result);
    }

    @Test
    public void eliminarLibro() {
        LibroRepository libroRepository = mock(LibroRepository.class);
        AutorRepository autorRepository = mock(AutorRepository.class);
        CategoriaRepository categoriaRepository = mock(CategoriaRepository.class);

        LibroController libroController = new LibroController(libroRepository, autorRepository, categoriaRepository);
        when(libroRepository.existsById(1L)).thenReturn(true);

        Boolean result = libroController.eliminarLibro(1L);
        assertEquals(true, result);
    }
}
