package net.yazidi.delta.web;

import static net.yazidi.delta.security.service.JwtService.generateToken;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import net.yazidi.delta.entity.Categorie;
import net.yazidi.delta.security.repository.AppUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class CategorieControllerTest {

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
    public void testCreateCategorie() throws Exception {
        Categorie categorie = Categorie.builder()
                .libelle("Categorie Test")
                .image("image_url")
                .build();

        mockMvc.perform(post("/api/categories")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categorie)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.libelle").value("Categorie Test"));
    }

    @Test
    public void testGetCategorie() throws Exception {
        mockMvc.perform(get("/api/categories/1")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testUpdateCategorie() throws Exception {
        Categorie updatedCategorie = Categorie.builder()
                .libelle("Updated Categorie")
                .image("updated_image_url")
                .build();

        mockMvc.perform(put("/api/categories/1")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedCategorie)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.libelle").value("Updated Categorie"));
    }

    @Test
    public void testDeleteCategorie() throws Exception {
        mockMvc.perform(delete("/api/categories/1")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetAllCategories() throws Exception {
        mockMvc.perform(get("/api/categories")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}
