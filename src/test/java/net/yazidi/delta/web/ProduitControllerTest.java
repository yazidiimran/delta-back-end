package net.yazidi.delta.web;

import static net.yazidi.delta.security.service.JwtService.generateToken;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import net.yazidi.delta.entity.Produit;
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
public class ProduitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String jwtToken;

    @Autowired
    private AppUserRepository userRepository;

    @BeforeEach
    public void setup() {
        jwtToken = generateToken("admin", "admin",userRepository);
    }

    @Test
    public void testCreateProduit() throws Exception {
        Produit produit = Produit.builder()
                .codeBarre("123456789")
                .libelle("Produit Test")
                .image("image_url")
                .build();

        mockMvc.perform(post("/api/produits")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(produit)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.codeBarre").value("123456789"))
                .andExpect(jsonPath("$.libelle").value("Produit Test"));
    }

    @Test
    public void testGetProduit() throws Exception {
        mockMvc.perform(get("/api/produits/1")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testUpdateProduit() throws Exception {
        Produit updatedProduit = Produit.builder()
                .codeBarre("987654321")
                .libelle("Updated Produit")
                .image("updated_image_url")
                .build();

        mockMvc.perform(put("/api/produits/1")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedProduit)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.codeBarre").value("987654321"))
                .andExpect(jsonPath("$.libelle").value("Updated Produit"));
    }

    @Test
    public void testDeleteProduit() throws Exception {
        mockMvc.perform(delete("/api/produits/1")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetAllProduits() throws Exception {
        mockMvc.perform(get("/api/produits")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }


}
