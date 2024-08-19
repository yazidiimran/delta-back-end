package net.yazidi.delta.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Achat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date = LocalDate.now();
    @OneToMany(mappedBy = "achat")
    private List<LignesAchat> lignesAchat = new ArrayList<>();
    @ManyToOne
    private Fournisseur fournisseur;
}
