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
    private float prixAchat;
    private float prixVente;
    private int quantite;
    @ManyToOne
    private Produit produit;
    @ManyToOne
    @JsonIgnore    
    private BonCommande bonCommande;
}
