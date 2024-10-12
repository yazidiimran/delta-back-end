package net.yazidi.delta.web;

import net.yazidi.delta.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.yazidi.delta.entity.Objectif;
import net.yazidi.delta.service.ObjectifService;

@Controller
@RequestMapping("/api/objectifs")
public class ObjectifController extends AbstractController<Objectif,Long>{

    @Autowired
    private ObjectifService objectifService;


    @Override
    IService getService() {
        return this.objectifService;
    }
}
