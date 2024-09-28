package net.yazidi.delta.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import net.yazidi.delta.entity.Client;
import net.yazidi.delta.service.ClientService;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/clients/{id}")
    public ResponseEntity<Client> getById(@PathVariable Long id) {
        Client client = clientService.getById(id);
        if (client != null) {
            return ResponseEntity.ok(client); // 200 OK si le client existe
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found si le client n'existe pas
        }
    }

    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getAll() {
        List<Client> clients = clientService.getAll();
        if (clients.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content si la liste est vide
        }
        return ResponseEntity.ok(clients); // 200 OK avec la liste des clients
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<Client> update(@RequestBody Client client, @PathVariable Long id) {
        try {
            Client updatedClient = clientService.update(client, id);
            return ResponseEntity.ok(updatedClient); // 200 OK avec le client mis à jour
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found si le client à mettre à jour n'existe pas
        }
    }

    @PostMapping("/clients")
    public ResponseEntity<Client> create(@RequestBody Client client) {
        try {
            Client newClient = clientService.create(client);
            return ResponseEntity.status(HttpStatus.CREATED).body(newClient); // 201 Created si le client est créé
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 400 Bad Request en cas d'erreur de création
        }
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            clientService.delete(id);
            return ResponseEntity.noContent().build(); // 204 No Content après suppression réussie
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found si le client à supprimer n'existe pas
        }
    }
}
