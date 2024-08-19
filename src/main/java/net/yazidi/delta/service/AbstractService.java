package net.yazidi.delta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import net.yazidi.delta.entity.Objectif;

public class AbstractService <ENTITY,ID>{

    @Autowired
    private JpaRepository<ENTITY,ID> repository;

    public ENTITY save(ENTITY entity){
        return this.repository.save(entity);
    }

    public ENTITY update(ENTITY entity, ID id) {
        return this.repository.save(entity);
    }

    public List<ENTITY> findAll() {
        return this.repository.findAll();
    }

    public ENTITY findById(ID id) {
        return this.repository.findById(id).get();
    }

    public void deleteById(ID id) {
        repository.deleteById(id);
    }
}
