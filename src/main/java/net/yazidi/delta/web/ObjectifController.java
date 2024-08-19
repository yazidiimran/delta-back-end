package net.yazidi.delta.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.yazidi.delta.entity.Objectif;
import net.yazidi.delta.service.ObjectifService;

@RestController
@RequestMapping("/api")
public class ObjectifController {

    @Autowired
    private ObjectifService objectifService;

    @PostMapping("/objectifs")
    public ResponseEntity<Objectif> create(@RequestBody Objectif objectif){
        return new ResponseEntity<>(this.objectifService.save(objectif),HttpStatus.CREATED);
    }

    @GetMapping("/objectifs")
    public ResponseEntity<List<Objectif>> findAll(){
        return new ResponseEntity<>(this.objectifService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/objectifs/{id}")
    public ResponseEntity<Objectif> findById(@PathVariable Long id){
        return new ResponseEntity<>(this.objectifService.findById(id),HttpStatus.OK);
    }

    @DeleteMapping("/objectifs/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        objectifService.deleteById(id);
        return new ResponseEntity<>("",HttpStatus.OK);
    }

    @PutMapping("/objectifs/{id}")
    public ResponseEntity<Objectif> update(@RequestBody Objectif objectif,@PathVariable Long id){
        return new ResponseEntity<>(objectifService.update(objectif,id),HttpStatus.OK);
    }
}
