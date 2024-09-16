package net.yazidi.delta.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import net.yazidi.delta.entity.Categorie;
import net.yazidi.delta.entity.Produit;
import net.yazidi.delta.service.CategoryService;

@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<List<Categorie>> getCategories(){
        List<Categorie> categories = this.categoryService.findAll();
        return new ResponseEntity<>(categories,HttpStatus.OK);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Categorie> getCategorie(@PathVariable Long id){
        Categorie categorie = this.categoryService.findById(id);
        return new ResponseEntity<>(categorie,HttpStatus.OK);
    }

    @GetMapping("/categories/{id}/produits")
    public ResponseEntity<List<Produit>> getProduitsByCategorie(@PathVariable Long id){
        List<Produit> produits = this.categoryService.findProductsByCategoryId(id);
        return new ResponseEntity<>(produits,HttpStatus.OK);
    }
}