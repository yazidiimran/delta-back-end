package net.yazidi.delta.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LignesBonCommande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float prix;
    private int quantite;
    private int tva;
    private int remise;
    @ManyToOne
    private Produit produit;
    @ManyToOne
    @JsonIgnore    
    private BonCommande bonCommande;
}
