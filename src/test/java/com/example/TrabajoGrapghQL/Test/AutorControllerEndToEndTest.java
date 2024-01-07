package com.example.TrabajoGrapghQL.Test;

import com.example.TrabajoGrapghQL.controllers.AutorController;
import com.example.TrabajoGrapghQL.domain.Autor;
import com.example.TrabajoGrapghQL.repositories.AutorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AutorControllerEndToEndTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testListarAutores() throws Exception {
        AutorRepository autorRepository = mock(AutorRepository.class);
        when(autorRepository.findAll()).thenReturn(Collections.emptyList());

        AutorController autorController = new AutorController(autorRepository);

        mockMvc.perform(MockMvcRequestBuilders.post("/graphql")
                        .content("{\"query\":\"{ listarAutores { id nombre } }\"}")
                        .contentType("application/json"))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType("application/json"))
                        .andExpect(jsonPath("$.data.listarAutores").isArray())
                        .andExpect(jsonPath("$.data.listarAutores").isEmpty());
    }

    @Test
    void testAgregarAutor() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/graphql")
                        .content("{\"query\":\"mutation { agregarAutor(nombre: \\\"Nuevo Autor\\\") { id nombre } }\"}")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.data.agregarAutor.id").isNumber())
                .andExpect(jsonPath("$.data.agregarAutor.nombre").value("Nuevo Autor"));
    }

    @Test
    void testEditarAutor() throws Exception {
        when(autorRepository.findById(any())).thenReturn(Optional.of(new Autor()));

        mockMvc.perform(MockMvcRequestBuilders.post("/graphql")
                        .content("{\"query\":\"mutation { editarAutor(id: 1, nombre: \\\"Nuevo Nombre\\\") { id nombre } }\"}")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.data.editarAutor.id").isNumber())
                .andExpect(jsonPath("$.data.editarAutor.nombre").value("Nuevo Nombre"));
    }

    @Test
    void testEliminarAutor() throws Exception {
        when(autorRepository.existsById(any())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/graphql")
                        .content("{\"query\":\"mutation { eliminarAutor(id: 1) }\"}")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.data.eliminarAutor").value(true));
    }
}

