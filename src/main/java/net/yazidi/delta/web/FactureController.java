package net.yazidi.delta.web;

import net.yazidi.delta.entity.BonLivraison;
import net.yazidi.delta.entity.Facture;
import net.yazidi.delta.service.BonLivraisonService;
import net.yazidi.delta.service.FactureService;
import net.yazidi.delta.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/factures")
public class FactureController extends AbstractController<Facture,Long>{
    
    @Autowired
    private FactureService factureService;

    @Override
    IService getService() {
        return this.factureService;
    }

}
