package net.yazidi.delta.service;

import jakarta.transaction.Transactional;
import net.yazidi.delta.entity.BonLivraison;
import net.yazidi.delta.repository.BonLivraisonRepository;
import net.yazidi.delta.security.models.AppUser;
import net.yazidi.delta.security.repository.AppUserRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class BonLivraisonService {

    @Autowired
    private AppUserRepository userRepository;

    @Autowired
    private BonLivraisonRepository bonLivraisonRepository;

    @Transactional
    public BonLivraison create(BonLivraison bonLivraison){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AppUser user = userRepository.findByUsername(auth.getName());
        bonLivraison.setUser(user);
        BonLivraison savedbonLivraison = bonLivraisonRepository.save(bonLivraison);
        return savedbonLivraison;
    }

    public List<BonLivraison> findAll() {
        return this.bonLivraisonRepository.findAll();
    }

    
}
