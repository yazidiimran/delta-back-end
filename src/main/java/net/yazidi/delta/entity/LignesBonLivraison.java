package net.yazidi.delta.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LignesBonLivraison {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double prix;
    private double quantite;
    private double tva;
    private double remise;
    @ManyToOne
    private Produit produit;
    @ManyToOne
    @JsonBackReference
    private BonLivraison bonLivraison;
}
