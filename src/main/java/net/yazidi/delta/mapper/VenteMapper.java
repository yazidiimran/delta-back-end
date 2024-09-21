package net.yazidi.delta.mapper;

import net.yazidi.delta.dto.VenteDto;
import net.yazidi.delta.entity.BonLivraison;

public class VenteMapper {
    public static VenteDto venteToVenteDTO(BonLivraison vente){
        VenteDto venteDto = VenteDto.builder()
            .id(vente.getId())
            .numero(vente.getNumero())
            .date(vente.getDate())
            .client(ClientMapper.ClientToClientDTO(vente.getClient()))
            .devis(DevisMapper.devistoDevisDto(vente.getDevis()))
            .lignesVente(LigneVenteMapper.LignesVenteToLignesVenteDTO(vente.getLignesVente()))
            .build();

        return venteDto;
    }
}
