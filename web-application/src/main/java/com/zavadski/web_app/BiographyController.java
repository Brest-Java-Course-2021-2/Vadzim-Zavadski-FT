package com.zavadski.web_app;

import com.zavadski.service.BiographyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BiographyController {

    public static final Logger LOG = LogManager.getLogger(BiographyController.class);

    private final BiographyService biographyService;

    public BiographyController(
            final BiographyService biographyService) {
        this.biographyService = biographyService;
    }

    @GetMapping("/biography/{player_id}")
    public String getBiographyByPlayerId(
            @PathVariable("player_id") final Integer playerId, final Model model) {

        LOG.info("Method getBiographyByPlayerId() started in class {}",
                getClass().getName());

        model.addAttribute("biography", biographyService.getBiographyByPlayerId(playerId));

        return "biography";
    }
}
