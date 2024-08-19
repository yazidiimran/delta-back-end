package net.yazidi.delta.service;

import net.yazidi.delta.dto.ProduitAchatDTO;
import net.yazidi.delta.entity.Achat;
import net.yazidi.delta.entity.LignesAchat;
import net.yazidi.delta.repository.AchatRepository;
import net.yazidi.delta.repository.LignesAchatRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AchatService {

    @Autowired
    private AchatRepository achatRepository;

    @Autowired
    private LignesAchatRepository lignesAchatRepository;

    public Achat create(Achat achat){
        Achat savedAchat = achatRepository.save(achat);
        for (LignesAchat ligneAchat:achat.getLignesAchat()) {
            ligneAchat.setAchat(savedAchat);
            lignesAchatRepository.save(ligneAchat);
        }
        return savedAchat;
    }

    public List<ProduitAchatDTO> getProduitsDTO() {
        return achatRepository.getProduitsDTO();
    }

    public List<ProduitAchatDTO> getProduitAchatDTOByCategorieID(Long id) {
        return achatRepository.getProduitDTOByCategorieID(id);
    }

    public List<ProduitAchatDTO> searchProduit(String keyword) {
        return achatRepository.searchProduit(keyword);
    }
}
