package net.yazidi.delta.service;

import net.yazidi.delta.entity.Entreprise;
import net.yazidi.delta.repository.EntrepriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntrepriseService {

    @Autowired
    private EntrepriseRepository entrepriseRepository;

    // Créer une nouvelle entreprise
    public Entreprise create(Entreprise entreprise) {
        return entrepriseRepository.save(entreprise);
    }

    // Récupérer toutes les entreprises
    public List<Entreprise> findAll() {
        return entrepriseRepository.findAll();
    }

    // Récupérer une entreprise par son ID
    public Optional<Entreprise> findById(Long id) {
        return entrepriseRepository.findById(id);
    }

    // Mettre à jour une entreprise existante
    public Entreprise update(Long id, Entreprise entrepriseDetails) throws Exception {
        Optional<Entreprise> optionalEntreprise = entrepriseRepository.findById(id);
        if (optionalEntreprise.isPresent()) {
            Entreprise entreprise = optionalEntreprise.get();
            // Mettre à jour les détails de l'entreprise
            entreprise.setRaisonSociale(entrepriseDetails.getRaisonSociale());
            entreprise.setDescription(entreprise.getDescription());
            entreprise.setEmail(entrepriseDetails.getEmail());
            entreprise.setTel(entrepriseDetails.getTel());
            entreprise.setFax(entrepriseDetails.getFax());
            entreprise.setSiteweb(entrepriseDetails.getSiteweb());
            entreprise.setRC(entrepriseDetails.getRC());
            entreprise.setICE(entrepriseDetails.getICE());
            entreprise.setIFF(entrepriseDetails.getIFF());
            entreprise.setCNSS(entrepriseDetails.getCNSS());
            entreprise.setAdresse(entrepriseDetails.getAdresse());
            entreprise.setVille(entrepriseDetails.getVille());
            entreprise.setPays(entrepriseDetails.getPays());
            return entrepriseRepository.save(entreprise);
        } else {
            throw new Exception("Entreprise not found for this id: " + id);
        }
    }

    // Supprimer une entreprise par son ID
    public void delete(Long id) {
        entrepriseRepository.deleteById(id);
    }
}
