package net.yazidi.delta.web;

import net.yazidi.delta.dto.ProduitEnStock;
import net.yazidi.delta.entity.BonCommande;
import net.yazidi.delta.service.BonCommandeService;

import java.util.List;

import net.yazidi.delta.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/boncommandes")
public class BonCommandeController extends AbstractController<BonCommande,Long>{
    
    @Autowired
    private BonCommandeService bonCommandeService;

    @Override
    IService getService() {
        return this.bonCommandeService;
    }

}