package net.yazidi.delta.repository;

import net.yazidi.delta.dto.ProduitAchatDTO;
import net.yazidi.delta.entity.Achat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AchatRepository extends JpaRepository<Achat,Long> {
    
    @Query(value="select new ProduitAchatDTO(p.id,c.id,u.id,p.codeBarre,p.libelle,l.prixVente,l.quantite,p.image)" +
            " from Achat a,LignesAchat l, Produit p,Categorie c, Unite u where a.id=l.achat.id " +
            "and l.produit.id=p.id and c.id=p.categorie.id and u.id=p.unite.id")
    List<ProduitAchatDTO> getProduitsDTO();

    @Query(value="select new ProduitAchatDTO(p.id,c.id,u.id,p.codeBarre,p.libelle,l.prixVente,l.quantite,p.image)" +
    " from Achat a,LignesAchat l, Produit p,Categorie c, Unite u where a.id=l.achat.id " +
    "and l.produit.id=p.id and c.id=p.categorie.id and u.id=p.unite.id and c.id=?1")
    List<ProduitAchatDTO> getProduitDTOByCategorieID(Long id);

    @Query(value="select new ProduitAchatDTO(p.id,c.id,u.id,p.codeBarre,p.libelle,l.prixVente,l.quantite,p.image)" +
    " from Achat a,LignesAchat l, Produit p,Categorie c, Unite u where a.id=l.achat.id " +
    "and l.produit.id=p.id and c.id=p.categorie.id and u.id=p.unite.id and p.codeBarre like %:keyword%")
    List<ProduitAchatDTO> searchProduit(String keyword);
}
