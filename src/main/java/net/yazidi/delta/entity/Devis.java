package net.yazidi.delta.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Devis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String reference;
    private LocalDate dateCreation;
    private LocalDate dateValidation;
    @ManyToOne
    private Client client;
    @OneToMany(mappedBy = "devis",fetch = FetchType.EAGER,cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<LignesDevis> lignesDevis;
}
