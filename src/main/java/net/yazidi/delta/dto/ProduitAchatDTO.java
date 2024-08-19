package net.yazidi.delta.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Builder
public class ProduitAchatDTO {
    @Id
    private Long id;
    private Long categorie;
    private Long unite;
    private String codeBarre;
    private String libelle;
    private float prix;
    private float quantite;
    private String image;
}
