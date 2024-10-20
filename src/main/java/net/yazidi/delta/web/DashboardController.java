package net.yazidi.delta.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import net.yazidi.delta.dto.StatistiqueDTO;
import net.yazidi.delta.service.DashboardService;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/dashboard/{annee}")
    public ResponseEntity<?> index(@PathVariable int annee) {
        try {
            StatistiqueDTO statistique = dashboardService.getStatistique(annee);
            if (statistique != null) {
                return ResponseEntity.ok(statistique); // 200 OK avec les statistiques
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 No Content si aucune statistique pour l'année donnée
            }
        } catch (Exception e) {
            // 500 Internal Server Error with error details
            Map<String, Object> body = new HashMap<>();
            body.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            body.put("message", "An internal server error occurred");
            body.put("error", e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);

//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 Internal Server Error en cas d'erreur serveur
        }
    }
}
