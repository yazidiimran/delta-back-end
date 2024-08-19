package net.yazidi.delta.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.yazidi.delta.entity.Produit;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LignesVenteDTO {
    private Long id;
    private double prix;
    private double quantite;
    private double tva;
    private double remise;
    private ProduitDTO produit;
}
