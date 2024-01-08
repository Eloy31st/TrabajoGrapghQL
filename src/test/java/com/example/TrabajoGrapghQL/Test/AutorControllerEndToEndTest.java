package com.example.TrabajoGrapghQL.Test;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTest;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;



import java.io.IOException;

@GraphQLTest
public class AutorControllerEndToEndTest {

    private GraphQLTestTemplate graphQLTestTemplate;

    @Test
    public void listarAutores() throws IOException {
        String query = "{ listarAutores { id nombre } }";
        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/listarAutores.graphql");
        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.isOk());
        Assertions.assertEquals(1, response.get("$.data.listarAutores.length", Integer.class));
    }

    @Test
    public void agregarAutor() throws IOException {
        String query = "mutation { agregarAutor(nombre: \"Autor 1\") { id nombre } }";
        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/agregarAutor.graphql");
        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.isOk());
        Assertions.assertEquals("Autor 1", response.get("$.data.agregarAutor.nombre", String.class));
    }

    @Test
    public void editarAutor() throws IOException {
        String query = "mutation { editarAutor(id: 1, nombre: \"Autor 2\") { id nombre } }";
        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/editarAutor.graphql");
        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.isOk());
        Assertions.assertEquals("Autor 2", response.get("$.data.editarAutor.nombre", String.class));
    }

    @Test
    public void eliminarAutor() throws IOException {
        String query = "mutation { eliminarAutor(id: 1) }";
        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/eliminarAutor.graphql");
        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.isOk());
        Assertions.assertEquals(true, response.get("$.data.eliminarAutor", Boolean.class));
    }
}