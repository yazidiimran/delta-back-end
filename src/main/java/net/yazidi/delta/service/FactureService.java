package net.yazidi.delta.service;

import jakarta.transaction.Transactional;
import net.yazidi.delta.entity.BonLivraison;
import net.yazidi.delta.entity.Facture;
import net.yazidi.delta.repository.BonLivraisonRepository;
import net.yazidi.delta.repository.FactureRepository;
import net.yazidi.delta.security.models.AppUser;
import net.yazidi.delta.security.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class FactureService extends AbstractService<Facture,Long>{

    @Autowired
    private AppUserRepository userRepository;

    @Autowired
    private FactureRepository factureRepository;

    @Transactional
    public Facture save(Facture facture){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AppUser user = userRepository.findByUsername(auth.getName());
        facture.setUser(user);
        Facture savedFacture = factureRepository.save(facture);
        return savedFacture;
    }

    @Override
    JpaRepository<Facture, Long> getRepository() {
        return this.factureRepository;
    }

}
