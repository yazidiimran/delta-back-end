package net.yazidi.delta.dto;

import lombok.*;
import net.yazidi.delta.entity.Devis;
import net.yazidi.delta.entity.LignesBonLivraison;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VenteDto {
    private Long id;
    private String numero;
    private LocalDate date;
    private ClientDTO client;
    private DevisDTO devis;
    private List<LignesVenteDTO> lignesBonLivraison = new ArrayList<>();
}
