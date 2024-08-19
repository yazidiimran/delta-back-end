package net.yazidi.delta.web;

import net.yazidi.delta.entity.Client;
import net.yazidi.delta.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Testing with TestRestTemplate and @Testcontainers (image mysql:8.0-debian)
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// activate automatic startup and stop of containers
@Testcontainers
// JPA drop and create table, good for testing
@TestPropertySource(properties = {"spring.jpa.hibernate.ddl-auto=create-drop"})
public class ClientControllerTest {

    @LocalServerPort
    private Integer port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String BASEURI;

    @Autowired
    ClientRepository clientRepository;

    // static, all tests share this postgres container
    @Container
    @ServiceConnection
    static MySQLContainer<?> postgres = new MySQLContainer<>(
            "mysql:8.1"
    );

    @BeforeEach
    void testSetUp() {

        BASEURI = "http://localhost:" + port;

        clientRepository.deleteAll();

        Client b1 = new Client(null,"yazidi","imran","yazidi@g.com","094949");
        Client b2 = new Client(null,"yazidi","imran","yazidi@g.com","094949");
        Client b3 = new Client(null,"yazidi","imran","yazidi@g.com","094949");
        Client b4 = new Client(null,"yazidi","imran","yazidi@g.com","094949");

        clientRepository.saveAll(List.of(b1, b2, b3, b4));
    }

    @Test
    void testFindAll() {

        // ResponseEntity<List> response = restTemplate.getForEntity(BASEURI + "/Clients", List.class);

        // find all Clients and return List<Client>
        ParameterizedTypeReference<List<Client>> typeRef = new ParameterizedTypeReference<>() {
        };
        ResponseEntity<List<Client>> response = restTemplate.exchange(
                BASEURI + "/clients",
                HttpMethod.GET,
                null,
                typeRef
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(4, response.getBody().size());

    }


//    @Test
//    void testFindByTitle() {
//        String title = "Client C";
//        ParameterizedTypeReference<List<Client>> typeRef = new ParameterizedTypeReference<>() {
//        };
//
//        // find Client C
//        ResponseEntity<List<Client>> response = restTemplate.exchange(
//                BASEURI + "/Clients/find/title/" + title,
//                HttpMethod.GET,
//                null,
//                typeRef
//        );
//
//        // test response code
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//
//        List<Client> list = response.getBody();
//        assert list != null;
//
//        assertEquals(1, list.size());
//
//        // Test Client C details
//        Client Client = list.get(0);
//        assertEquals("Client C", Client.getTitle());
//        assertEquals(BigDecimal.valueOf(29.99), Client.getPrice());
//        assertEquals(LocalDate.of(2023, 6, 10), Client.getPublishDate());
//
//    }
//
//    @Test
//    void testFindByPublishedDateAfter() {
//
//        String date = "2023-07-01";
//        ParameterizedTypeReference<List<Client>> typeRef = new ParameterizedTypeReference<>() {
//        };
//
//        // find Client C
//        ResponseEntity<List<Client>> response = restTemplate.exchange(
//                BASEURI + "/Clients/find/date-after/" + date,
//                HttpMethod.GET,
//                null,
//                typeRef
//        );
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//
//        // test list of objects
//        List<Client> result = response.getBody();
//        assert result != null;
//
//        assertEquals(2, result.size());
//
//        assertThat(result).extracting(Client::getTitle)
//                .containsExactlyInAnyOrder(
//                        "Client A", "Client B");
//        assertThat(result).extracting(Client::getPrice)
//                .containsExactlyInAnyOrder(
//                        new BigDecimal("9.99"), new BigDecimal("19.99"));
//        assertThat(result).extracting(Client::getPublishDate)
//                .containsExactlyInAnyOrder
//                        (LocalDate.parse("2023-08-31"), LocalDate.parse("2023-07-31"));
//    }
//
//    @Test
//    public void testDeleteById() {
//
//        List<Client> list = clientRepository.findByTitle("Client A");
//        Client ClientA = list.get(0);
//
//        // get Client A id
//        Long id = ClientA.getId();
//
//        // delete by id
//        ResponseEntity<Void> response = restTemplate.exchange(
//                BASEURI + "/Clients/" + id,
//                HttpMethod.DELETE,
//                null,
//                Void.class
//        );
//
//        // test 204
//        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
//
//        // find Client A again, ensure no result
//        List<Client> listAgain = clientRepository.findByTitle("Client A");
//        assertEquals(0, listAgain.size());
//
//    }
//
//    @Test
//    public void testCreate() {
//
//        // Create a new Client E
//        Client newClient = new Client("Client E", new BigDecimal("9.99"), LocalDate.parse("2023-09-14"));
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Type", "application/json");
//        HttpEntity<Client> request = new HttpEntity<>(newClient, headers);
//
//        // test POST save
//        ResponseEntity<Client> responseEntity =
//                restTemplate.postForEntity(BASEURI + "/Clients", request, Client.class);
//
//        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
//
//        // find Client E
//        List<Client> list = clientRepository.findByTitle("Client E");
//
//        // Test Client E details
//        Client Client = list.get(0);
//        assertEquals("Client E", Client.getTitle());
//        assertEquals(BigDecimal.valueOf(9.99), Client.getPrice());
//        assertEquals(LocalDate.of(2023, 9, 14), Client.getPublishDate());
//
//    }
//
//    /**
//     * Client b4 = new Client("Client D",
//     * BigDecimal.valueOf(39.99),
//     * LocalDate.of(2023, 5, 5));
//     */
//    @Test
//    public void testUpdate() {
//        // Find Client D
//        Client ClientD = clientRepository.findByTitle("Client D").get(0);
//        Long id = ClientD.getId();
//
//        // Update the Client details
//        ClientD.setTitle("Client DDD");
//        ClientD.setPrice(new BigDecimal("199.99"));
//        ClientD.setPublishDate(LocalDate.of(2024, 1, 31));
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Type", "application/json");
//
//        // put the updated Client in HttpEntity
//        HttpEntity<Client> request = new HttpEntity<>(ClientD, headers);
//
//        // Perform the PUT request to update the Client
//        ResponseEntity<Client> responseEntity = restTemplate.exchange(
//                "http://localhost:" + port + "/Clients",
//                HttpMethod.PUT,
//                request,
//                Client.class
//        );
//
//        // ensure OK
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//
//        // verify the updated Client
//        Client updatedClient = clientRepository.findById(id).orElseThrow();
//
//        assertEquals(id, updatedClient.getId());
//        assertEquals("Client DDD", updatedClient.getTitle());
//        assertEquals(BigDecimal.valueOf(199.99), updatedClient.getPrice());
//        assertEquals(LocalDate.of(2024, 1, 31), updatedClient.getPublishDate());
//
//    }



}

