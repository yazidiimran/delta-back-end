package net.yazidi.delta.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import net.yazidi.delta.dto.StatistiqueDTO;
import net.yazidi.delta.repository.ClientRepository;
import net.yazidi.delta.repository.DashboardRepository;
import net.yazidi.delta.repository.FournisseurRepository;
import net.yazidi.delta.repository.VenteRepository;

@Service
public class DashboardService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private FournisseurRepository fournsisseurRepository;

   

    @Autowired
    private VenteRepository venteRepository;

    public StatistiqueDTO getStatistique(int annee) {
        StatistiqueDTO statistiqueDTO = StatistiqueDTO.builder()
        .CA(venteRepository.getCA(annee))
        .CAP(venteRepository.getCAP(annee))
        .CAS(venteRepository.getEvolutionCA(annee))
        .CMDS(venteRepository.getEvolutionCMD(annee))
        .CACAT(venteRepository.getCAbyCategorie(annee))
        .CABYCLIENT(venteRepository.getCAbyClient(annee))
        .CABYCOMMERCIAL(venteRepository.getCAbyCommercial(annee))
        .clients(clientRepository.findAll().size())
        .fournsisseurs(fournsisseurRepository.findAll().size())
        .commandes(venteRepository.getCmdCount(annee))
        .build();
        return statistiqueDTO;
    }

}
