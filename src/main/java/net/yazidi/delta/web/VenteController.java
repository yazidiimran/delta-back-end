package net.yazidi.delta.web;

import net.yazidi.delta.dto.VenteDto;
import net.yazidi.delta.entity.Vente;
import net.yazidi.delta.mapper.VenteMapper;
import net.yazidi.delta.service.VenteService;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class VenteController {
    @Autowired
    private VenteService venteService;
    
    @PostMapping("/ventes")
    public Vente create(@RequestBody Vente vente){
        return venteService.create(vente);
    }

    @PutMapping("/ventes/{id}")
    public Vente update(@RequestBody Vente vente,@PathVariable Long id){
        return venteService.update(vente,id);
    }

    @GetMapping("/ventes")
    public List<VenteDto> findAll(){
        List<VenteDto> venteDtos = new ArrayList<>();
        venteService.findAll().forEach(vente->{
            venteDtos.add(VenteMapper.venteToVenteDTO(vente));
        });
        return venteDtos;
    }
}
