package net.yazidi.delta.mapper;

import java.util.List;

import net.yazidi.delta.dto.ProduitDTO;
import net.yazidi.delta.entity.Produit;

public class ProduitMapper {

    public static ProduitDTO produitToProduitDTO(Produit produit) {
        ProduitDTO produitDTO = ProduitDTO.builder()
        .id(produit.getId())
        .categorie(produit.getCategorie().getId())
        .unite(produit.getUnite().getId())
        .codeBarre(produit.getCodeBarre())
        .image(produit.getImage())
        .libelle(produit.getLibelle())
        .build();
        return produitDTO;
    }

    public static List<ProduitDTO> produitsToProduitsDTO(List<Produit> produits) {
        return produits.stream().map(produit->ProduitMapper.produitToProduitDTO(produit)).toList();
    }

}
