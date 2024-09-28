package net.yazidi.delta.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Entreprise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String raisonSociale;

    private String description;

    private String email;

    private String tel;

    private String fax;

    private String siteweb;

    private String RC; // Registre de Commerce

    private String ICE; // Identifiant Commun de l’Entreprise

    private String IFF; // Identifiant Fiscal

    private String CNSS; // Caisse Nationale de Sécurité Sociale

    private String adresse;

    private String ville;

    private String pays;

}
