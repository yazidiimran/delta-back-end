package net.yazidi.delta.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.yazidi.delta.entity.BonCommande;
import net.yazidi.delta.entity.Fournisseur;
import net.yazidi.delta.entity.LignesBonCommande;
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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static net.yazidi.delta.security.service.JwtService.generateToken;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class BonCommandeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private String jwtToken;

    @Autowired
    private AppUserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        this.jwtToken = generateToken("admin", "admin", userRepository);
    }

    @Test
    public void testCreateBonCommande() throws Exception {
        BonCommande bonCommande = createBonCommande();

        mockMvc.perform(post("/api/boncommandes")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bonCommande)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.reference").value("BC-765"));
    }

    @Test
    public void testGetBonCommandeById() throws Exception {
        // Assume a BonCommande with ID 1 exists
        Long bonCommandeId = 1L;

        mockMvc.perform(get("/api/boncommandes/{id}", bonCommandeId)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(bonCommandeId));
    }

    @Test
    public void testUpdateBonCommande() throws Exception {
        // Assume a BonCommande with ID 1 exists
        Long bonCommandeId = 1L;
        BonCommande updatedBonCommande = createBonCommande();
        updatedBonCommande.setStatut("UPDATED");

        mockMvc.perform(put("/api/boncommandes/{id}", bonCommandeId)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedBonCommande)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statut").value("UPDATED"));
    }

    @Test
    public void testDeleteBonCommande() throws Exception {
        // Assume a BonCommande with ID 1 exists
        Long bonCommandeId = 1L;

        mockMvc.perform(delete("/api/boncommandes/{id}", bonCommandeId)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken))
                .andExpect(status().isNoContent());
    }

    private BonCommande createBonCommande() {
        Produit produit = Produit.builder()
                .codeBarre("123456789")
                .libelle("Produit Test")
                .image("image_url")
                .build();

        LignesBonCommande lb = LignesBonCommande.builder()
                .prix(9f)
                .tva(20)
                .quantite(3)
                .produit(produit)
                .remise(0)
                .build();

        return BonCommande.builder()
                .date(LocalDate.now())
                .fournisseur(new Fournisseur(1L))
                .reference("BC-765")
                .statut("CREATED")
                .lignesBonCommande(List.of(lb))
                .build();
    }
}
