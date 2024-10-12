package net.yazidi.delta.web;

import static net.yazidi.delta.security.service.JwtService.generateToken;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import net.yazidi.delta.entity.Entreprise;
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
public class EntrepriseControllerTest {

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
    public void testCreateEntreprise() throws Exception {
        Entreprise entreprise = Entreprise.builder()
                .raisonSociale("Entreprise Test")
                .description("Description de l'entreprise")
                .email("test@entreprise.com")
                .tel("0123456789")
                .fax("0123456780")
                .siteweb("http://entreprise.com")
                .RC("RC123456")
                .ICE("ICE123456")
                .IFF("IFF123456")
                .CNSS("CNSS123456")
                .adresse("123 Rue Principale")
                .ville("Casablanca")
                .pays("Maroc")
                .build();

        mockMvc.perform(post("/api/entreprises")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(entreprise)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.raisonSociale").value("Entreprise Test"))
                .andExpect(jsonPath("$.email").value("test@entreprise.com"));
    }

    @Test
    public void testGetEntreprise() throws Exception {
        mockMvc.perform(get("/api/entreprises/1")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testUpdateEntreprise() throws Exception {
        Entreprise updatedEntreprise = Entreprise.builder()
                .raisonSociale("Updated Entreprise")
                .description("Updated description")
                .email("updated@entreprise.com")
                .tel("0987654321")
                .fax("0987654320")
                .siteweb("http://updated-entreprise.com")
                .RC("RC654321")
                .ICE("ICE654321")
                .IFF("IFF654321")
                .CNSS("CNSS654321")
                .adresse("456 Nouvelle Rue")
                .ville("Rabat")
                .pays("Maroc")
                .build();

        mockMvc.perform(put("/api/entreprises/1")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedEntreprise)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.raisonSociale").value("Updated Entreprise"))
                .andExpect(jsonPath("$.email").value("updated@entreprise.com"));
    }

    @Test
    public void testDeleteEntreprise() throws Exception {
        mockMvc.perform(delete("/api/entreprises/1")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetAllEntreprises() throws Exception {
        mockMvc.perform(get("/api/entreprises")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}
