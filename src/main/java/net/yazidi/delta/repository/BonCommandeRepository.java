package net.yazidi.delta.repository;

import net.yazidi.delta.dto.ProduitEnStock;
import net.yazidi.delta.entity.BonCommande;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BonCommandeRepository extends JpaRepository<BonCommande,Long> {


    @Query(value="select new ProduitEnStock(p.id,c.id,u.id,p.codeBarre,p.libelle,l.prixVente,l.quantite,p.image)" +
    " from BonCommande a,LignesBonCommande l, Produit p,Categorie c, Unite u where a.id=l.bonCommande.id " +
    "and l.produit.id=p.id and c.id=p.categorie.id and u.id=p.unite.id and (p.codeBarre like %:keyword% or p.libelle like %:keyword%)")
    List<ProduitEnStock> searchProduit(String keyword);
    
}
