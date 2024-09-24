package net.yazidi.delta.web;

import net.yazidi.delta.dto.ProduitEnStock;
import net.yazidi.delta.entity.BonCommande;
import net.yazidi.delta.service.BonCommandeService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BonCommandeController {
    @Autowired
    private BonCommandeService bonCommandeService;

    @PostMapping("/boncommandes")
    public BonCommande save(@RequestBody BonCommande bonCommande){
        return bonCommandeService.save(bonCommande);
    }

    @GetMapping("/boncommandes")
    public List<BonCommande> findAll(){
        return bonCommandeService.findAll();
    }

    @GetMapping("/boncommandes/{id}")
    public BonCommande findOneById(@PathVariable Long id){
        return bonCommandeService.findOneById(id);
    }

    @GetMapping("/boncommandes/search/{keyword}")
    public List<ProduitEnStock> searchProductInStock(@PathVariable String keyword){
        return bonCommandeService.searchProduit(keyword);
    }

    @DeleteMapping("/boncommandes/{id}")
    public void delete(@PathVariable Long id){
        bonCommandeService.delete(id);
    }
}

