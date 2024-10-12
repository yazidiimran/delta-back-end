package net.yazidi.delta.service;

import net.yazidi.delta.entity.Entreprise;
import net.yazidi.delta.entity.Objectif;
import net.yazidi.delta.repository.EntrepriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntrepriseService extends AbstractService <Entreprise,Long>{

    @Autowired
    EntrepriseRepository entrepriseRepository;

    @Override
    JpaRepository<Entreprise, Long> getRepository() {
        return this.entrepriseRepository;
    }
}
