package net.yazidi.delta.web;

import net.yazidi.delta.entity.Fournisseur;
import net.yazidi.delta.service.FournisseurService;
import net.yazidi.delta.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/fournisseurs")
public class FournisseurController extends AbstractController<Fournisseur,Long> {

    @Autowired
    private FournisseurService fournisseurService;

    @Override
    IService getService() {
        return this.fournisseurService;
    }
}
