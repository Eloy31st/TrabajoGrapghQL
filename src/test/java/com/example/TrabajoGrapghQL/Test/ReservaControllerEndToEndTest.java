package com.example.TrabajoGrapghQL.Test;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTest;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@GraphQLTest
public class ReservaControllerEndToEndTest {
    private GraphQLTestTemplate graphQLTestTemplate;

    @Test
    public void listarReservas() throws Exception {
        String query = "{ listarReservas { id nombre } }";
        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/listarReservas.graphql");
        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.isOk());
        Assertions.assertEquals(1, response.get("$.data.listarReservas.length", Integer.class));
    }

    @Test
    public void agregarReserva() throws Exception {
        String query = "mutation { agregarReserva(libroId: 1) { id nombre } }";
        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/agregarReserva.graphql");
        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.isOk());
        Assertions.assertEquals("Reserva 1", response.get("$.data.agregarReserva.nombre", String.class));
    }

    @Test
    public void editarReserva() throws Exception {
        String query = "mutation { editarReserva(libroId: 1) { id nombre } }";
        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/editarReserva.graphql");
        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.isOk());
        Assertions.assertEquals("Reserva 2", response.get("$.data.editarReserva.nombre", String.class));
    }

    @Test
    public void eliminarReserva() throws Exception {
        String query = "mutation { eliminarReserva(libroId: 1) }";
        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/eliminarReserva.graphql");
        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.isOk());
        Assertions.assertEquals(true, response.get("$.data.eliminarReserva", Boolean.class));
    }
}
