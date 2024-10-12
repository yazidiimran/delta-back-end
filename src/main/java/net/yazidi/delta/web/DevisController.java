package net.yazidi.delta.web;

import java.util.List;

import net.yazidi.delta.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.yazidi.delta.entity.Devis;
import net.yazidi.delta.service.DevisService;

@Controller
@RequestMapping("/api/devis")
public class DevisController extends AbstractController<Devis,Long>{

    @Autowired
    private DevisService devisService;


    @Override
    IService getService() {
        return this.devisService;
    }
}
