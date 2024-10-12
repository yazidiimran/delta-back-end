package net.yazidi.delta.web;

import static net.yazidi.delta.security.service.JwtService.generateToken;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import net.yazidi.delta.entity.Client;
import net.yazidi.delta.security.repository.AppUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String jwtToken;

    @Autowired
    private AppUserRepository userRepository;

    @BeforeEach
    public void setup() {
        jwtToken = generateToken("admin", "admin", userRepository);
    }

    @Test
    public void testCreateClient() throws Exception {
        Client client = Client.builder()
                .nom("John")
                .prenom("Doe")
                .adresse("123 Main St")
                .tel("1234567890")
                .build();

        mockMvc.perform(post("/api/clients")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(client)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nom").value("John"))
                .andExpect(jsonPath("$.prenom").value("Doe"));
    }

    @Test
    public void testGetClient() throws Exception {
        mockMvc.perform(get("/api/clients/1")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testUpdateClient() throws Exception {
        Client updatedClient = Client.builder()
                .nom("Jane")
                .prenom("Smith")
                .adresse("456 Elm St")
                .tel("0987654321")
                .build();

        mockMvc.perform(put("/api/clients/1")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedClient)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("Jane"))
                .andExpect(jsonPath("$.prenom").value("Smith"));
    }

    @Test
    public void testDeleteClient() throws Exception {
        mockMvc.perform(delete("/api/clients/1")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetAllClients() throws Exception {
        mockMvc.perform(get("/api/clients")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}
