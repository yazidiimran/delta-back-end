package net.yazidi.delta.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
    private String image;
    @OneToMany(mappedBy = "categorie")
    @JsonIgnore
    private List<Produit> produits;
}

