package net.yazidi.delta.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import net.yazidi.delta.entity.Devis;
import net.yazidi.delta.service.DevisService;

@RestController
@RequestMapping("/api")
public class DevisController {

    @Autowired
    private DevisService devisService;

    @PostMapping("/devises")
    public ResponseEntity<Devis> create(@RequestBody Devis devis){
        return new ResponseEntity<>(this.devisService.create(devis),HttpStatus.CREATED);
    }

    @GetMapping("/devises")
    public ResponseEntity<List<Devis>> finAll(){
        return new ResponseEntity<>(this.devisService.findAll(),HttpStatus.OK);
    }
    
}
