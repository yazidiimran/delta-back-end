package net.yazidi.delta.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import net.yazidi.delta.security.models.AppUser;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String reference;
    private LocalDate date;
    @ManyToOne
    private Client client;
    @ManyToOne
    private AppUser user;
    @OneToMany(mappedBy = "facture",fetch = FetchType.EAGER,cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<LignesFacture> lignesFacture;
    private String statut;

    public void setLignesFacture(List<LignesFacture> lignesFacture) {
        this.lignesFacture = lignesFacture;
        for (LignesFacture ligneFactures :lignesFacture) {
            ligneFactures.setFacture(this);
        }
    }
}
