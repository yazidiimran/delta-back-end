package net.yazidi.delta.dto;

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
public class DevisDTO {
    @Id
    private long id;
    private String numero;
    private LocalDate dateCreation;
    private LocalDate dateValidation;
}
