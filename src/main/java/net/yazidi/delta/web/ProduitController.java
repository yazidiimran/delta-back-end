package net.yazidi.delta.web;

import net.yazidi.delta.dto.ProduitDTO;
import net.yazidi.delta.entity.Produit;
import net.yazidi.delta.mapper.ProduitMapper;
import net.yazidi.delta.service.ProduitService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class ProduitController {
    @Autowired
    private ProduitService produitService;

    @PostMapping("/produits")
    public ResponseEntity<Produit> create(@RequestBody Produit produit){
        return new ResponseEntity<>(produitService.save(produit),HttpStatus.CREATED);
    }

    @PutMapping("/produits/{id}")
    public ResponseEntity<Produit> update(@RequestBody Produit produit,@PathVariable Long id){
        return new ResponseEntity<>(produitService.update(produit,id),HttpStatus.OK);
    }

    @DeleteMapping("/produits/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        produitService.delete(id);
        return new ResponseEntity<>("",HttpStatus.OK);
    }

    @GetMapping("/produits")
    public ResponseEntity<List<ProduitDTO>> getProduits(){
        List<ProduitDTO> produits = ProduitMapper.produitsToProduitsDTO(this.produitService.findAll());
        return new ResponseEntity<>(produits,HttpStatus.OK);
    }

    @GetMapping("/produits/{id}")
    public ResponseEntity<ProduitDTO> getProduitById(@PathVariable Long id){
        ProduitDTO produit = ProduitMapper.produitToProduitDTO(this.produitService.findById(id));
        return new ResponseEntity<>(produit,HttpStatus.OK);
    }

    
}
