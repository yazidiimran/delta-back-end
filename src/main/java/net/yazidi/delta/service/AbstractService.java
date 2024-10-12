package net.yazidi.delta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import net.yazidi.delta.entity.Objectif;

public abstract class AbstractService <ENTITY,ID> implements IService<ENTITY,ID>{


    abstract JpaRepository<ENTITY,ID> getRepository();

    @Override
    public List<ENTITY> findAll() {
        return this.getRepository().findAll();
    }

    @Override
    public void deleteById(ID id) {
        this.getRepository().deleteById(id);
    }

    @Override
    public ENTITY save(ENTITY entity) {
        return this.getRepository().save(entity);
    }

    @Override
    public ENTITY findById(ID id) {
        return this.getRepository().findById(id).get();
    }
}
