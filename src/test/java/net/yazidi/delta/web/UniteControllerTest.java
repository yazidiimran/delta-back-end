package net.yazidi.delta.web;

import static net.yazidi.delta.security.service.JwtService.generateToken;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import net.yazidi.delta.entity.Unite;
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
public class UniteControllerTest {

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
    public void testCreateUnite() throws Exception {
        Unite unite = Unite.builder()
                .libelle("Unite Test")
                .build();

        mockMvc.perform(post("/api/unites")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(unite)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.libelle").value("Unite Test"));
    }

    @Test
    public void testGetUnite() throws Exception {
        mockMvc.perform(get("/api/unites/1")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testUpdateUnite() throws Exception {
        Unite updatedUnite = Unite.builder()
                .libelle("Updated Unite")
                .build();

        mockMvc.perform(put("/api/unites/1")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedUnite)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.libelle").value("Updated Unite"));
    }

    @Test
    public void testDeleteUnite() throws Exception {
        mockMvc.perform(delete("/api/unites/1")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetAllUnites() throws Exception {
        mockMvc.perform(get("/api/unites")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}
