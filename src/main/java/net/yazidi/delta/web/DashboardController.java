package net.yazidi.delta.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.yazidi.delta.dto.StatistiqueDTO;
import net.yazidi.delta.service.DashboardService;

@RestController
@RequestMapping("/api")
public class DashboardController {

    @Autowired
    private DashboardService DashboardService;

    @GetMapping("/dashboard/{annee}")
    public StatistiqueDTO index(@PathVariable int annee){
        return DashboardService.getStatistique(annee);
    }

}
