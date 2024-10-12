package net.yazidi.delta.web;

import net.yazidi.delta.entity.Parametre;
import net.yazidi.delta.repository.ParametreRepository;
import net.yazidi.delta.service.IService;
import net.yazidi.delta.service.ParametreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/parametres")
public class ParametreController extends AbstractController<Parametre,Long> {

    @Autowired
    private ParametreService parametreService;

    @Override
    IService getService() {
        return this.parametreService;
    }
}
