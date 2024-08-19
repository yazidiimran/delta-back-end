package net.yazidi.delta.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LignesAchat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float prixAchat;
    private float prixVente;
    private int quantite;
    @ManyToOne
    @JsonBackReference
    private Achat achat;
    @ManyToOne
    private Produit produit;
    
}
