package net.yazidi.delta.web;

import net.yazidi.delta.entity.BonLivraison;
import net.yazidi.delta.service.BonLivraisonService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BonLivraisonController {
    
    @Autowired
    private BonLivraisonService bonLivraisonService;

    @PostMapping("/bonLivraisons")
    public ResponseEntity<BonLivraison> create(@RequestBody BonLivraison bonLivraison) {
        try {
            BonLivraison savedBonLivraison = bonLivraisonService.create(bonLivraison);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedBonLivraison); // 201 Created
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 400 Bad Request en cas d'erreur
        }
    }

    @GetMapping("/bonLivraisons")
    public ResponseEntity<List<BonLivraison>> findAll() {
        List<BonLivraison> bonLivraisons = bonLivraisonService.findAll();
        if (bonLivraisons.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 No Content si la liste est vide
        }
        return ResponseEntity.ok(bonLivraisons); // 200 OK avec la liste des bons de livraison
    }

}
