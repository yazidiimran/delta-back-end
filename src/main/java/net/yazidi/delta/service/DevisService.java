package net.yazidi.delta.service;

import net.yazidi.delta.entity.Devis;
import net.yazidi.delta.repository.DevisRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class DevisService extends AbstractService<Devis,Long>{

    @Autowired
    private DevisRepository devisRepository;

    @Override
    JpaRepository<Devis, Long> getRepository() {
        return devisRepository;
    }
}
