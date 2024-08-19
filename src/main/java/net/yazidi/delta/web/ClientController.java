package net.yazidi.delta.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.yazidi.delta.entity.Client;
import net.yazidi.delta.service.ClientService;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/clients/{id}")
    public Client getById(@PathVariable Long id) throws Exception{
        return clientService.getById(id);
    }

    @GetMapping("/clients")
    public List<Client> getAll(){
        return clientService.getAll();
    }

    @PutMapping("/clients/{id}")
    public Client update(@RequestBody Client client,@PathVariable Long id) throws Exception{
        return clientService.update(client, id);
    }

    @PostMapping("/clients")
    public Client create(@RequestBody Client client){
        return clientService.create(client);
    }

    @DeleteMapping("/clients/{id}")
    public void delete(@PathVariable Long id) throws Exception{
        clientService.delete(id);
    }

}
