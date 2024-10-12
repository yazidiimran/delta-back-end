package net.yazidi.delta.web;

import net.yazidi.delta.entity.Produit;
import net.yazidi.delta.service.ProduitService;

import net.yazidi.delta.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/api/produits")
public class ProduitController extends AbstractController<Produit,Long>{
    @Autowired
    private ProduitService produitService;

    @Override
    public IService getService() {
        return this.produitService;
    }

}
