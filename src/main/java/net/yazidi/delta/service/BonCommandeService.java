package net.yazidi.delta.service;

import net.yazidi.delta.dto.ProduitEnStock;
import net.yazidi.delta.entity.BonCommande;
import net.yazidi.delta.repository.BonCommandeRepository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BonCommandeService {

    @Autowired
    private BonCommandeRepository boncommandeRepository;

     
    public BonCommande save(BonCommande boncommande){
        return boncommandeRepository.save(boncommande);
    }

    public BonCommande findOneById(Long id){
        return boncommandeRepository.findById(id).get();
    }

    public void delete(Long id){
        boncommandeRepository.deleteById(id);
    }
    

    public List<ProduitEnStock> searchProduit(String keyword) {
        return boncommandeRepository.searchProduit(keyword);
    }


    public List<BonCommande> findAll() {
        return boncommandeRepository.findAll();
    }

    public BonCommande findOnById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findOnById'");
    }
}
