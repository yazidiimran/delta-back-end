package net.yazidi.delta.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.ArrayList;

 

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
    private String image;
    @OneToMany(mappedBy = "categorie" ,fetch = FetchType.EAGER,cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Produit> produits = new ArrayList<>();

     
    public void setProduits(List<Produit> produits) {
        this.produits = produits;
        for (Produit produit :produits) {
            produit.setCategorie(this);
        }
    }

    public List<Produit> getProduits() {
        return this.produits ;
    }
}

