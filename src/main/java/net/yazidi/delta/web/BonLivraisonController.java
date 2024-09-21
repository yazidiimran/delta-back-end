package net.yazidi.delta.web;

import net.yazidi.delta.entity.BonLivraison;
import net.yazidi.delta.service.BonLivraisonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BonLivraisonController {
    
    @Autowired
    private BonLivraisonService bonLivraisonService;
    
    @PostMapping("/bonLivraisons")
    public BonLivraison create(@RequestBody BonLivraison bonLivraison){
        return bonLivraisonService.create(bonLivraison);
    }

}
