package net.yazidi.delta.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import net.yazidi.delta.dto.StatistiqueDTO;
import net.yazidi.delta.repository.BonCommandeRepository;
import net.yazidi.delta.repository.BonLivraisonRepository;
import net.yazidi.delta.repository.ClientRepository;
import net.yazidi.delta.repository.FournisseurRepository;


@Service
public class DashboardService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private FournisseurRepository fournsisseurRepository;

   

    @Autowired
    private BonLivraisonRepository bonLivraisonRepository;

    @Autowired
    private BonCommandeRepository bonCommandeRepository;
    
    public StatistiqueDTO getStatistique(int annee) {
        StatistiqueDTO statistiqueDTO = StatistiqueDTO.builder()
        .CA(bonLivraisonRepository.getCA(annee))
        .CAP(bonLivraisonRepository.getCAP(annee))
        .CAS(bonLivraisonRepository.getEvolutionCA(annee))
        .CMDS(bonLivraisonRepository.getEvolutionCMD(annee))
        .CACAT(bonLivraisonRepository.getCAbyCategorie(annee))
        .CABYCLIENT(bonLivraisonRepository.getCAbyClient(annee))
        .CABYCOMMERCIAL(bonLivraisonRepository.getCAbyCommercial(annee))
        .clients(clientRepository.findAll().size())
        .fournsisseurs(fournsisseurRepository.findAll().size())
        .bonLivraisonCount(bonLivraisonRepository.getCmdCount(annee))
        .bonCommandesCount(bonCommandeRepository.getBonCommandesCount(annee))
        .build();
        return statistiqueDTO;
    }

}
