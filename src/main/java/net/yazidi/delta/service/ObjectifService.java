package net.yazidi.delta.service;

import net.yazidi.delta.repository.ObjectifRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import net.yazidi.delta.entity.Objectif;

@Service
public class ObjectifService extends AbstractService <Objectif,Long>{

    @Autowired
    private ObjectifRepository objectifRepository;

    @Override
    JpaRepository<Objectif, Long> getRepository() {
        return this.objectifRepository;
    }
}
