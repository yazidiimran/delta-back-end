package net.yazidi.delta.service;

import net.yazidi.delta.dto.ProduitDTO;
import net.yazidi.delta.entity.Produit;
import net.yazidi.delta.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitService {
    @Autowired
    private ProduitRepository produitRepository;

    public Produit save(Produit produit) {
        return produitRepository.save(produit);
    }

    public List<Produit> findAll() {
        return produitRepository.findAll();
    }

    public Object getProduitDTO(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProduitDTO'");
    }

    public Produit findById(Long id) {
        return produitRepository.findById(id).get();
    }


    public Produit update(Produit produit, Long id) {
        return produitRepository.save(produit);
    }


    public void delete(Long id) {
        this.produitRepository.deleteById(id);
    }
}
