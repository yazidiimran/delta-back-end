package net.yazidi.delta.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.yazidi.delta.entity.Devis;
import net.yazidi.delta.service.DevisService;

@RestController
@RequestMapping("/api")
public class DevisController {

    @Autowired
    private DevisService devisService;

    @PostMapping("/devises")
    public ResponseEntity<Devis> create(@RequestBody Devis devis) {
        try {
            Devis createdDevis = devisService.create(devis);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDevis); // 201 Created si le devis est créé
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 400 Bad Request en cas d'erreur
        }
    }

    @GetMapping("/devises")
    public ResponseEntity<List<Devis>> findAll() {
        List<Devis> devisList = devisService.findAll();
        if (devisList.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content si aucun devis n'est trouvé
        }
        return ResponseEntity.ok(devisList); // 200 OK avec la liste des devis
    }
}
