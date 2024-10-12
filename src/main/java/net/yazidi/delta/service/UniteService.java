package net.yazidi.delta.service;

import net.yazidi.delta.entity.Unite;
import net.yazidi.delta.repository.UniteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UniteService extends AbstractService<Unite,Long>{

    @Autowired
    private UniteRepository uniteRepository;

    @Override
    JpaRepository<Unite, Long> getRepository() {
        return this.uniteRepository;
    }
}
