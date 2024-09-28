package net.yazidi.delta.web;

import net.yazidi.delta.dto.ProduitEnStock;
import net.yazidi.delta.entity.BonCommande;
import net.yazidi.delta.service.BonCommandeService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BonCommandeController {
    
    @Autowired
    private BonCommandeService bonCommandeService;

    @PostMapping("/boncommandes")
    public ResponseEntity<BonCommande> save(@RequestBody BonCommande bonCommande) {
        try {
            BonCommande savedCommande = bonCommandeService.save(bonCommande);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCommande); // 201 Created
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 400 Bad Request
        }
    }

    @GetMapping("/boncommandes")
    public ResponseEntity<List<BonCommande>> findAll() {
        List<BonCommande> commandes = bonCommandeService.findAll();
        if (commandes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 No Content si vide
        }
        return ResponseEntity.ok(commandes); // 200 OK avec les données
    }

    @GetMapping("/boncommandes/{id}")
    public ResponseEntity<BonCommande> findOneById(@PathVariable Long id) {
        BonCommande bonCommande = bonCommandeService.findOneById(id);
        if (bonCommande != null) {
            return ResponseEntity.ok(bonCommande); // 200 OK avec la commande trouvée
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found si la commande n'existe pas
        }
    }

    @GetMapping("/boncommandes/search/{keyword}")
    public ResponseEntity<List<ProduitEnStock>> searchProductInStock(@PathVariable String keyword) {
        List<ProduitEnStock> produits = bonCommandeService.searchProduit(keyword);
        if (produits.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 No Content si aucun produit trouvé
        }
        return ResponseEntity.ok(produits); // 200 OK avec les résultats de recherche
    }

    @DeleteMapping("/boncommandes/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            bonCommandeService.delete(id);
            return ResponseEntity.noContent().build(); // 204 No Content après suppression réussie
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found si la commande n'existe pas
        }
    }
}