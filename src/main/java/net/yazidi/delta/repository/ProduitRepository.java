package net.yazidi.delta.repository;

import net.yazidi.delta.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduitRepository extends JpaRepository<Produit,Long> {
 
    
}
