package net.yazidi.delta.web;

import static net.yazidi.delta.security.service.JwtService.generateToken;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import net.yazidi.delta.entity.Fournisseur;
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
public class FournisseurControllerTest {

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
    public void testCreateFournisseur() throws Exception {
        Fournisseur fournisseur = Fournisseur.builder()
                .nom("Doe")
                .prenom("John")
                .adresse("123 Main St")
                .tel("123-456-7890")
                .build();

        mockMvc.perform(post("/api/fournisseurs")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(fournisseur)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nom").value("Doe"))
                .andExpect(jsonPath("$.prenom").value("John"));
    }

    @Test
    public void testGetFournisseur() throws Exception {
        mockMvc.perform(get("/api/fournisseurs/1")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testUpdateFournisseur() throws Exception {
        Fournisseur updatedFournisseur = Fournisseur.builder()
                .nom("Smith")
                .prenom("Jane")
                .adresse("456 Market Ave")
                .tel("987-654-3210")
                .build();

        mockMvc.perform(put("/api/fournisseurs/1")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedFournisseur)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("Smith"))
                .andExpect(jsonPath("$.prenom").value("Jane"));
    }

    @Test
    public void testDeleteFournisseur() throws Exception {
        mockMvc.perform(delete("/api/fournisseurs/1")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetAllFournisseurs() throws Exception {
        mockMvc.perform(get("/api/fournisseurs")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}
