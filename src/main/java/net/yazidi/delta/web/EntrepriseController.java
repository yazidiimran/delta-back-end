package net.yazidi.delta.web;

import net.yazidi.delta.entity.Entreprise;
import net.yazidi.delta.service.EntrepriseService;
import net.yazidi.delta.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/entreprises")
public class EntrepriseController extends AbstractController<Entreprise,Long>{

    @Autowired
    private EntrepriseService entrepriseService;


    @Override
    IService getService() {
        return this.entrepriseService;
    }
}
