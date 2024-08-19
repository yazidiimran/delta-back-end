package net.yazidi.delta.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Facture {
    @Id
    private long id;
    private String numero;
    private LocalDate date;
    @ManyToOne
    private Client client;
}
