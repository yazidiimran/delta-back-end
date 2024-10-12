package net.yazidi.delta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import net.yazidi.delta.entity.Client;
import net.yazidi.delta.repository.ClientRepository;

@Service
public class ClientService extends AbstractService<Client,Long>{

    @Autowired
    private ClientRepository clientRepository;

    @Override
    JpaRepository<Client, Long> getRepository() {
        return this.clientRepository;
    }
}
