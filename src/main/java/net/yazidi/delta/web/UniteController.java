package net.yazidi.delta.web;

import net.yazidi.delta.entity.Unite;
import net.yazidi.delta.service.IService;
import net.yazidi.delta.service.UniteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/unites")
public class UniteController extends AbstractController<Unite,Long> {

    @Autowired
    private UniteService uniteService;
    @Override
    IService getService() {
        return this.uniteService;
    }
}
