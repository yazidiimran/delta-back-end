package net.yazidi.delta.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;
import net.yazidi.delta.entity.Categorie;
import net.yazidi.delta.entity.Produit;
import net.yazidi.delta.service.CategoryService;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<List<Categorie>> getCategories() {
        List<Categorie> categories = categoryService.findAll();
        if (categories.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content si la liste est vide
        }
        return ResponseEntity.ok(categories); // 200 OK avec la liste des catégories
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Categorie> getCategorie(@PathVariable Long id) {
        Categorie categorie = categoryService.findById(id);
        if (categorie != null) {
            return ResponseEntity.ok(categorie); // 200 OK avec la catégorie trouvée
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found si la catégorie n'est pas trouvée
        }
    }

    @GetMapping("/categories/{id}/produits")
    public ResponseEntity<List<Produit>> getProduitsByCategorie(@PathVariable Long id) {
        List<Produit> produits = categoryService.findProductsByCategoryId(id);
        if (produits.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content si aucun produit n'est trouvé
        }
        return ResponseEntity.ok(produits); // 200 OK avec les produits
    }
}
