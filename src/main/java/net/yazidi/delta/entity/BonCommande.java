package net.yazidi.delta.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BonCommande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String reference;
    private LocalDate date;
    @OneToMany(mappedBy = "bonCommande",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LignesBonCommande> lignesBonCommande;
    @ManyToOne
    private Fournisseur fournisseur;
}
