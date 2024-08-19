package net.yazidi.delta.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codeBarre;
    private String libelle;
    private String image;
    @ManyToOne
    private Unite unite;
    @ManyToOne
    private Categorie categorie;

}
