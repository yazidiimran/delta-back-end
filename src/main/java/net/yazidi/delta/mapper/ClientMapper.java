package net.yazidi.delta.mapper;

import net.yazidi.delta.dto.ClientDTO;
import net.yazidi.delta.entity.Client;

public class ClientMapper {

    public static ClientDTO ClientToClientDTO(Client client) {
        ClientDTO clientDTO = ClientDTO.builder()
            .id(client.getId())
            .nom(client.getNom())
            .prenom(client.getPrenom())
            .tel(client.getTel())
            .adresse(client.getAdresse())
            .build();
        return clientDTO;
    }

}
