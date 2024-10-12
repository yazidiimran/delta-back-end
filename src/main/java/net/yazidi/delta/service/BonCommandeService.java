package net.yazidi.delta.service;

import net.yazidi.delta.entity.BonCommande;
import net.yazidi.delta.repository.BonCommandeRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class BonCommandeService extends AbstractService<BonCommande,Long>{

    @Autowired
    private BonCommandeRepository boncommandeRepository;

    @Override
    JpaRepository<BonCommande, Long> getRepository() {
        return this.boncommandeRepository;
    }

}
