package net.yazidi.delta.service;

import jakarta.transaction.Transactional;
import net.yazidi.delta.entity.BonCommande;
import net.yazidi.delta.entity.BonLivraison;
import net.yazidi.delta.repository.BonLivraisonRepository;
import net.yazidi.delta.security.models.AppUser;
import net.yazidi.delta.security.repository.AppUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class BonLivraisonService extends AbstractService<BonLivraison,Long>{

    @Autowired
    private AppUserRepository userRepository;

    @Autowired
    private BonLivraisonRepository bonLivraisonRepository;

    @Transactional
    public BonLivraison save(BonLivraison bonLivraison){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AppUser user = userRepository.findByUsername(auth.getName());
        bonLivraison.setUser(user);
        BonLivraison savedBonLivraison = bonLivraisonRepository.save(bonLivraison);
        return savedBonLivraison;
    }

    @Override
    JpaRepository<BonLivraison, Long> getRepository() {
        return this.bonLivraisonRepository;
    }
<<<<<<< HEAD
    
=======

    public BonLivraison findOneById(Long id){
        return bonLivraisonRepository.findById(id).get();
    }

>>>>>>> origin/main
}
