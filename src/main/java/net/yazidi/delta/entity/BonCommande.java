package net.yazidi.delta.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @OneToMany(mappedBy = "bonCommande",fetch = FetchType.EAGER,cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LignesBonCommande> lignesBonCommande;
    @ManyToOne
    private Fournisseur fournisseur;
    private String statut;

    public void setLignesBonCommande(List<LignesBonCommande> lignesBonCommande) {
        this.lignesBonCommande = lignesBonCommande;
        for (LignesBonCommande ligneBonCommande :lignesBonCommande) {
            ligneBonCommande.setBonCommande(this);  // Ensure the parent reference is set in child
        }
    }
}
