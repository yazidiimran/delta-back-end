package net.yazidi.delta.service;

import jakarta.transaction.Transactional;
import net.yazidi.delta.entity.LignesVente;
import net.yazidi.delta.entity.Vente;
import net.yazidi.delta.repository.LignesVenteRepository;
import net.yazidi.delta.repository.VenteRepository;
import net.yazidi.delta.security.models.AppUser;
import net.yazidi.delta.security.repository.AppUserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class VenteService {

    @Autowired
    private AppUserRepository userRepository;

    @Autowired
    private VenteRepository venteRepository;

    @Autowired
    private LignesVenteRepository lignesVenteRepository;

    @Transactional
    public Vente create(Vente vente){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AppUser user = userRepository.findByUsername(auth.getName());
        vente.setUser(user);
        vente.setDevis(vente.getDevis()==null?null:vente.getDevis());
        Vente savedVente = venteRepository.save(vente);
        for (LignesVente ligneVente:vente.getLignesVente()) {
            ligneVente.setVente(savedVente);
            lignesVenteRepository.save(ligneVente);
        }
        return savedVente;
    }

    public List<Vente> findAll() {
        return this.venteRepository.findAll();
    }

    @Transactional
    public Vente update(Vente vente, Long id) {

        Vente savedVente = venteRepository.findById(id).get();

        for (LignesVente ligneVente:vente.getLignesVente()) {
            ligneVente.setVente(savedVente);
            lignesVenteRepository.save(ligneVente);
        }
        
        return venteRepository.save(vente);
    }
}
