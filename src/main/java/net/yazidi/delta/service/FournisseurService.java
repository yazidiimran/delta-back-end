package net.yazidi.delta.service;

import net.yazidi.delta.entity.Fournisseur;
import net.yazidi.delta.repository.FournisseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class FournisseurService extends AbstractService<Fournisseur,Long> {

   @Autowired
   private FournisseurRepository fournisseurRepository;

   @Override
   JpaRepository<Fournisseur, Long> getRepository() {
        return this.fournisseurRepository;
   }
}
