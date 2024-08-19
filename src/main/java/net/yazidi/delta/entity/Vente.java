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
public class Vente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    private Date date = new Date();
    @ManyToOne
    private Client client;
    @ManyToOne
    private AppUser user;
    @ManyToOne
    private Devis devis = new Devis();
    @OneToMany(mappedBy = "vente",cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<LignesVente> lignesVente;
}
