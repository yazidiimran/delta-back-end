package net.yazidi.delta.service;

import net.yazidi.delta.entity.Parametre;
import net.yazidi.delta.repository.ParametreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


@Service
public class ParametreService extends AbstractService<Parametre,Long> {
    @Autowired
    private ParametreRepository parametreRepository;


    @Override
    JpaRepository<Parametre, Long> getRepository() {
        return this.parametreRepository;
    }
}
