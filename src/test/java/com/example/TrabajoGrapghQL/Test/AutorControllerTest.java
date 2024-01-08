package com.example.TrabajoGrapghQL.Test;

import com.example.TrabajoGrapghQL.controllers.AutorController;
import com.example.TrabajoGrapghQL.domain.Autor;
import com.example.TrabajoGrapghQL.repositories.AutorRepository;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AutorControllerTest {
    @Test
    public void listarAutores() {
        AutorRepository autorRepository = mock(AutorRepository.class);

        AutorController autorController = new AutorController(autorRepository);
        List<Autor> autores = Arrays.asList(new Autor(), new Autor());
        when(autorRepository.findAll()).thenReturn(autores);

        Iterable<Autor> result = autorController.listarAutores();
        assertEquals(autores, result);
    }

    @Test
    public void agregarAutor() {
        AutorRepository autorRepository = mock(AutorRepository.class);

        AutorController autorController = new AutorController(autorRepository);
        Autor autor = new Autor();
        autor.setNombre("nombre");
        when(autorRepository.save(any())).thenReturn(autor);

        Autor result = autorController.agregarAutor("nombre");
        assertEquals(autor, result);
    }

    @Test
    public void editarAutor() {
        AutorRepository autorRepository = mock(AutorRepository.class);

        AutorController autorController = new AutorController(autorRepository);
        Autor autor = new Autor();
        autor.setNombre("nombre");
        when(autorRepository.findById(any())).thenReturn(java.util.Optional.of(autor));
        when(autorRepository.save(any())).thenReturn(autor);

        Autor result = autorController.editarAutor(1L, "nombre");
        assertEquals(autor, result);
    }

    @Test
    public void eliminarAutor() {
        AutorRepository autorRepository = mock(AutorRepository.class);

        AutorController autorController = new AutorController(autorRepository);
        Autor autor = new Autor();
        autor.setNombre("nombre");
        when(autorRepository.existsById(any())).thenReturn(true);

        Boolean result = autorController.eliminarAutor(1L);
        assertEquals(true, result);
    }
}
