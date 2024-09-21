package net.yazidi.delta.mapper;

import java.util.ArrayList;
import java.util.List;

import net.yazidi.delta.dto.LignesVenteDTO;
import net.yazidi.delta.entity.LignesBonLivraison;

public class LigneVenteMapper {

    public static LignesVenteDTO LigneVenteToLigneVenteDTO(LignesBonLivraison ligne) {
        LignesVenteDTO lignesVenteDTO = LignesVenteDTO.builder()
            .id(ligne.getId())
            .prix(ligne.getPrix())
            .quantite(ligne.getQuantite())
            .tva(ligne.getTva())
            .remise(ligne.getRemise())
            .produit(ProduitMapper.produitToProduitDTO(ligne.getProduit()))
            .build();
        return lignesVenteDTO;
    }

    public static List<LignesVenteDTO> LignesVenteToLignesVenteDTO(List<LignesBonLivraison> lignesVente){
        List<LignesVenteDTO> lignesVenteDTOS = new ArrayList<>();
        lignesVente.forEach(ligneVente->{
            lignesVenteDTOS.add(LigneVenteToLigneVenteDTO(ligneVente));
        });
        return lignesVenteDTOS;
    }

}
