package net.yazidi.delta.repository;

import net.yazidi.delta.dto.*;
import net.yazidi.delta.entity.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FactureRepository extends JpaRepository<Facture,Long> {

   
    @Query(value="select sum(l.prix*l.quantite-(l.prix*l.quantite*l.remise/100)+(l.prix*l.quantite*l.tva/100)) from Facture v,LignesFacture l where l.facture.id=v.id and year(v.date)=?1")
    Long getCA(int annee);

   
    @Query(value="select montant from Objectif where annee=?1")
    Long getCAP(int annee);

    @Query(value="select new CADTO(1.0,sum(l.prix*l.quantite-(l.prix*l.quantite*l.remise/100)+(l.prix*l.quantite*l.tva/100)),date) from Facture v,LignesFacture l where l.facture.id=v.id and year(v.date)=?1 group by date")
    List<CADTO> getEvolutionCA(int annee);

    @Query(value="select new CATDTO(1.0,c.libelle,sum(l.prix*l.quantite-(l.prix*l.quantite*l.remise/100)+(l.prix*l.quantite*l.tva/100))) from Facture v,LignesFacture l, Produit p, Categorie c where l.produit.id = p.id and p.categorie.id = c.id and l.facture.id=v.id and year(v.date)=?1 group by c.libelle")
    List<CATDTO> getCAbyCategorie(int annee);

    @Query(value="select new CABYCLIENT(1.0,c.nom,sum(l.prix*l.quantite-(l.prix*l.quantite*l.remise/100)+(l.prix*l.quantite*l.tva/100))) from Facture v,LignesFacture l, Client c where v.client.id = c.id and l.facture.id=v.id and year(v.date)=?1 group by c.nom")
    List<CABYCLIENT> getCAbyClient(int annee);

    @Query(value="select new CABYCOMMERCIAL(1.0,u.username,sum(l.prix*l.quantite-(l.prix*l.quantite*l.remise/100)+(l.prix*l.quantite*l.tva/100))) from Facture v,LignesFacture l, AppUser u where v.user.id = u.id and l.facture.id=v.id and year(v.date)=?1 group by u.username")
    List<CABYCOMMERCIAL> getCAbyCommercial(int annee);;

    @Query(value="select new CMDDTO(1,count(*),date) from Facture  where year(date)=?1 group by date")
    List<CMDDTO> getEvolutionCMD(int annee);

    @Query(value="select count(*) from Facture v where year(v.date)=?1")
    int getCmdCount(int annee);

}
