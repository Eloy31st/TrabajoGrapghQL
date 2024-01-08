package com.example.TrabajoGrapghQL.Test;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTest;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


@GraphQLTest
public class CategoriaControllerEndToEndTest {
    private GraphQLTestTemplate graphQLTestTemplate;

    @Test
    public void listarCategorias() throws Exception {
        String query = "{ listarCategorias { id nombre } }";
        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/listarCategorias.graphql");
        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.isOk());
        Assertions.assertEquals(1, response.get("$.data.listarCategorias.length", Integer.class));
    }

    @Test
    public void agregarCategoria() throws Exception {
        String query = "mutation { agregarCategoria(nombre: \"Categoria 1\") { id nombre } }";
        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/agregarCategoria.graphql");
        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.isOk());
        Assertions.assertEquals("Categoria 1", response.get("$.data.agregarCategoria.nombre", String.class));
    }

    @Test
    public void editarCategoria() throws Exception {
        String query = "mutation { editarCategoria(id: 1, nombre: \"Categoria 2\") { id nombre } }";
        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/editarCategoria.graphql");
        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.isOk());
        Assertions.assertEquals("Categoria 2", response.get("$.data.editarCategoria.nombre", String.class));
    }

    @Test
    public void eliminarCategoria() throws Exception {
        String query = "mutation { eliminarCategoria(id: 1) }";
        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/eliminarCategoria.graphql");
        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.isOk());
        Assertions.assertEquals(true, response.get("$.data.eliminarCategoria", Boolean.class));
    }

}
