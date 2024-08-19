package net.yazidi.delta.mapper;

import net.yazidi.delta.dto.VenteDto;
import net.yazidi.delta.entity.Vente;

public class VenteMapper {
    public static VenteDto venteToVenteDTO(Vente vente){
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
