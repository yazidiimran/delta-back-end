package net.yazidi.delta.web;

import net.yazidi.delta.entity.BonCommande;
import net.yazidi.delta.entity.BonLivraison;
import net.yazidi.delta.service.BonLivraisonService;

import java.util.List;

import net.yazidi.delta.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/bonlivraisons")
public class BonLivraisonController extends AbstractController<BonLivraison,Long>{
    
    @Autowired
    private BonLivraisonService bonLivraisonService;

    @Override
    IService getService() {
        return this.bonLivraisonService;
    }

}
