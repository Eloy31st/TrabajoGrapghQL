package com.example.TrabajoGrapghQL.Test;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.graphql.spring.boot.test.GraphQLTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@GraphQLTest
public class LibroControllerEndToEndTest {
    private GraphQLTestTemplate graphQLTestTemplate;

    @Test
    public void listarLibros() throws Exception {
        String query = "{ listarLibros { id nombre } }";
        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/listarLibros.graphql");
        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.isOk());
        Assertions.assertEquals(1, response.get("$.data.listarLibros.length", Integer.class));
    }

    @Test
    public void agregarLibro() throws Exception {
        String query = "mutation { agregarLibro(nombre: \"Libro 1\", categoriaId: 1, autorId: 1) { id nombre } }";
        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/agregarLibro.graphql");
        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.isOk());
        Assertions.assertEquals("Libro 1", response.get("$.data.agregarLibro.nombre", String.class));
    }

    @Test
    public void editarLibro() throws Exception {
        String query = "mutation { editarLibro(id: 1, nombre: \"Libro 2\", categoriaId: 1, autorId: 1) { id nombre } }";
        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/editarLibro.graphql");
        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.isOk());
        Assertions.assertEquals("Libro 2", response.get("$.data.editarLibro.nombre", String.class));
    }

    @Test
    public void eliminarLibro() throws Exception {
        String query = "mutation { eliminarLibro(id: 1) }";
        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/eliminarLibro.graphql");
        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.isOk());
        Assertions.assertEquals(true, response.get("$.data.eliminarLibro", Boolean.class));
    }

    @Test
    public void buscarLibrosPorCategoria() throws Exception {
        String query = "{ buscarLibrosPorCategoria(categoriaId: 1) { id nombre } }";
        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/buscarLibrosPorCategoria.graphql");
        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.isOk());
        Assertions.assertEquals(1, response.get("$.data.buscarLibrosPorCategoria.length", Integer.class));
    }

    @Test
    public void buscarLibrosPorAutor() throws Exception {
        String query = "{ buscarLibrosPorAutor(autorId: 1) { id nombre } }";
        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/buscarLibrosPorAutor.graphql");
        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.isOk());
        Assertions.assertEquals(1, response.get("$.data.buscarLibrosPorAutor.length", Integer.class));
    }

    @Test
    public void buscarLibrosPorTitulo() throws Exception {
        String query = "{ buscarLibrosPorTitulo(titulo: \"Libro 1\") { id nombre } }";
        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/buscarLibrosPorTitulo.graphql");
        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.isOk());
        Assertions.assertEquals(1, response.get("$.data.buscarLibrosPorTitulo.length", Integer.class));
    }

}
