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
    private Date date = new Date();
    @ManyToOne
    private Client client;
    @ManyToOne
    private AppUser user;
    @ManyToOne
    private Devis devis;
    @OneToMany(mappedBy = "bonLivraison",cascade = CascadeType.MERGE)
    private List<LignesBonLivraison> lignesVente;
    private String statut;
}
