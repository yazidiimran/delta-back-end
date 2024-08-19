package net.yazidi.delta.service;

import net.yazidi.delta.entity.Devis;
import net.yazidi.delta.repository.DevisRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DevisService {

    @Autowired
    private DevisRepository devisRepository;

    public Devis create(Devis devis){
        return devisRepository.save(devis);
    }

    public List<Devis> findAll() {
        return devisRepository.findAll();
    }

}
