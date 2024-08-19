package net.yazidi.delta.mapper;

import net.yazidi.delta.dto.DevisDTO;
import net.yazidi.delta.entity.Devis;

public class DevisMapper {

    public static DevisDTO devistoDevisDto(Devis devis){
        if(devis==null) return null;
        DevisDTO devisDTO = DevisDTO.builder()
            .id(devis.getId())
            .numero(devis.getNumero())
            .dateValidation(devis.getDateValidation())
            .dateCreation(devis.getDateCreation())
            .build();

        return devisDTO;
    }
}
