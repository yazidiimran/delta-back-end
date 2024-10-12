package net.yazidi.delta.web;

import static net.yazidi.delta.security.service.JwtService.generateToken;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import net.yazidi.delta.entity.Objectif;
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
public class ObjectifControllerTest {

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
    public void testCreateObjectif() throws Exception {
        Objectif objectif = Objectif.builder()
                .annee(2024)
                .montant(50000.0)
                .build();

        mockMvc.perform(post("/api/objectifs")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(objectif)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.annee").value(2024))
                .andExpect(jsonPath("$.montant").value(50000.0));
    }

    @Test
    public void testGetObjectif() throws Exception {
        mockMvc.perform(get("/api/objectifs/1")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testUpdateObjectif() throws Exception {
        Objectif updatedObjectif = Objectif.builder()
                .annee(2025)
                .montant(75000.0)
                .build();

        mockMvc.perform(put("/api/objectifs/1")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedObjectif)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.annee").value(2025))
                .andExpect(jsonPath("$.montant").value(75000.0));
    }

    @Test
    public void testDeleteObjectif() throws Exception {
        mockMvc.perform(delete("/api/objectifs/1")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetAllObjectifs() throws Exception {
        mockMvc.perform(get("/api/objectifs")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}
