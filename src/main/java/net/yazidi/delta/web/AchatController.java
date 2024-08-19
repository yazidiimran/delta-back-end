package net.yazidi.delta.web;

import net.yazidi.delta.dto.ProduitAchatDTO;
import net.yazidi.delta.entity.Achat;
import net.yazidi.delta.service.AchatService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AchatController {
    @Autowired
    private AchatService achatService;

    @PostMapping("/achats")
    public Achat create(@RequestBody Achat achat){
        return achatService.create(achat);
    }

    @GetMapping("/achats/produits")
    public ResponseEntity<List<ProduitAchatDTO>> getAchatsProduits(){
        return new ResponseEntity<>(this.achatService.getProduitsDTO(),HttpStatus.OK);
    }

    @GetMapping("/achats/produits/{id}")
    public ResponseEntity<List<ProduitAchatDTO>> getProduitsByCategorieId(@PathVariable Long id){
        return new ResponseEntity<>(this.achatService.getProduitAchatDTOByCategorieID(id),HttpStatus.OK);
    }

    @GetMapping("/achats/produits/search/{keyword}")
    public ResponseEntity<List<ProduitAchatDTO>> searchProduit(@PathVariable String keyword){
        return new ResponseEntity<>(this.achatService.searchProduit(keyword),HttpStatus.OK);
    }
}
