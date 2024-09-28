package net.yazidi.delta.web;

import net.yazidi.delta.entity.Entreprise;
import net.yazidi.delta.service.EntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EntrepriseController {

    @Autowired
    private EntrepriseService entrepriseService;

    // Créer une nouvelle entreprise
    @PostMapping("/entreprises")
    public ResponseEntity<Entreprise> create(@RequestBody Entreprise entreprise) {
        Entreprise createdEntreprise = entrepriseService.create(entreprise);
        return new ResponseEntity<>(createdEntreprise, HttpStatus.CREATED);
    }

    // Récupérer toutes les entreprises
    @GetMapping("/entreprises")
    public ResponseEntity<List<Entreprise>> findAll() {
        List<Entreprise> entreprises = entrepriseService.findAll();
        return new ResponseEntity<>(entreprises, HttpStatus.OK);
    }

    // Récupérer une entreprise par son ID
    @GetMapping("/entreprises/{id}")
    public ResponseEntity<Entreprise> findById(@PathVariable Long id) {
        Optional<Entreprise> entreprise = entrepriseService.findById(id);
        return entreprise.map(ResponseEntity::ok)
                         .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Mettre à jour une entreprise existante
    @PutMapping("/entreprises/{id}")
    public ResponseEntity<Entreprise> update(@PathVariable Long id, @RequestBody Entreprise entrepriseDetails) {
        try {
            Entreprise updatedEntreprise = entrepriseService.update(id, entrepriseDetails);
            return ResponseEntity.ok(updatedEntreprise);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Supprimer une entreprise par son ID
    @DeleteMapping("/entreprises/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        entrepriseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
