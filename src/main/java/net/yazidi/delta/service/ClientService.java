package net.yazidi.delta.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.yazidi.delta.entity.Client;
import net.yazidi.delta.repository.ClientRepository;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client create(Client client){
        return clientRepository.save(client);
    }

    public Client update(Client client,Long id) throws Exception{
        if(clientRepository.findById(id).get()!=null){
            return clientRepository.save(client);
        }else{
            throw new Exception("Client inexistant !");
        }
    }

    public void delete(Long id) throws Exception{
        if(clientRepository.findById(id).get()!=null){
            clientRepository.deleteById(id);
        }else{
            throw new Exception("Client inexistant !");
        }
    }

    public Client getById(Long id)  {        
        return clientRepository.findById(id).get();
    }

    public List<Client> getAll(){
        return clientRepository.findAll();
    }

}
