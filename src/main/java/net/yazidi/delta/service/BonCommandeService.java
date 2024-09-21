package net.yazidi.delta.service;

import net.yazidi.delta.dto.ProduitEnStock;
import net.yazidi.delta.entity.BonCommande;
import net.yazidi.delta.entity.LignesBonCommande;
import net.yazidi.delta.entity.Produit;
import net.yazidi.delta.repository.BonCommandeRepository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BonCommandeService {

    @Autowired
    private BonCommandeRepository boncommandeRepository;

     
    public BonCommande create(BonCommande boncommande){
        BonCommande savedboncommande = boncommandeRepository.save(boncommande);
        return savedboncommande;
    }
    

    public List<ProduitEnStock> searchProduit(String keyword) {
        return boncommandeRepository.searchProduit(keyword);
    }
}
