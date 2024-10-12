package net.yazidi.delta.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import net.yazidi.delta.security.models.AppUser;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BonLivraison {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String reference;
    private LocalDate date;
    @ManyToOne
    private Client client;
    @ManyToOne
    private AppUser user;
    @ManyToOne
    private Devis devis;
    @OneToMany(mappedBy = "bonLivraison",fetch = FetchType.EAGER,cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<LignesBonLivraison> lignesBonLivraison;
    private String statut;

    public void setLignesBonLivraison(List<LignesBonLivraison> lignesBonLivraison) {
        this.lignesBonLivraison = lignesBonLivraison;
        for (LignesBonLivraison ligneBonLivraison :lignesBonLivraison) {
            ligneBonLivraison.setBonLivraison(this);
        }
    }
}
