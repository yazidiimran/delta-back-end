package net.yazidi.delta.dto;

import lombok.*;
import net.yazidi.delta.entity.LignesBonCommande;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AchatDto {
    private Long id;
    private Date date;
    private Long idFournisseur;
    private List<LignesBonCommande> lignesAchatList;
}
