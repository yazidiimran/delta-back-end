package net.yazidi.delta.service;

import net.yazidi.delta.entity.Produit;
import net.yazidi.delta.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitService extends AbstractService<Produit,Long> {
    @Autowired
    private ProduitRepository produitRepository;

    @Override
    JpaRepository<Produit, Long> getRepository() {
        return this.produitRepository;
    }
}
