package com.zavadski.web_app;

import com.zavadski.model.Player;
import com.zavadski.service.PlayerService;
import com.zavadski.service.TeamService;
import com.zavadski.web_app.validators.PlayerValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class PlayerController {

    private static final Logger logger = LoggerFactory.getLogger(PlayerController.class);

    private final PlayerService playerService;
    private final TeamService teamService;

    private final PlayerValidator playerValidator;

    public PlayerController(TeamService teamService,
                            PlayerService playerService,
                            PlayerValidator playerValidator) {
        this.teamService = teamService;
        this.playerService = playerService;
        this.playerValidator = playerValidator;
    }

    /**
     * Goto players list page.
     *
     * @return view name
     */
    @GetMapping(value = "/players")
    public final String players(Model model) {
        model.addAttribute("players", playerService.findAllPlayers());
        return "players";
    }

    /**
     * Goto edit player page.
     *
     * @return view name
     */
    @GetMapping(value = "/player/{id}")
    public final String gotoEditPlayerPage(@PathVariable Integer id, Model model) {
        logger.debug("gotoEditPlayerPage(id:{},model:{})", id, model);
        Optional<Player> optionalPlayer = playerService.getPlayerById(id);
        if (optionalPlayer.isPresent()) {
            model.addAttribute("isNew", false);
            model.addAttribute("player", optionalPlayer.get());
            model.addAttribute("teams", teamService.findAllTeams());
            return "player";
        } else {
            return "redirect:players";
        }
    }

    /**
     * Goto new player page.
     *
     * @return view name
     */
    @GetMapping(value = "/player")
    public final String gotoAddPlayerPage(Model model) {
        logger.debug("gotoAddPlayerPage({})", model);
        model.addAttribute("isNew", true);
        model.addAttribute("player", new Player().setTeamId(0));
        model.addAttribute("teams", teamService.findAllTeams());
        return "player";
    }

    /**
     * Persist new player into persistence storage.
     *
     * @param player new player with filled data.
     * @return view name
     */
    @PostMapping(value = "/player")
    public String addPlayer(Player player, BindingResult result) {
        logger.debug("addPlayer({}, {})", player);
        playerValidator.validate(player, result);
        if (result.hasErrors()) {
            return "player";
        } else {
            this.playerService.create(player);
            return "redirect:/players";
        }
    }

    /**
     * Update player.
     *
     * @param player player with filled data.
     * @return view name
     */
    @PostMapping(value = "/player/{id}")
    public String updatePlayer(Player player, BindingResult result) {
        logger.debug("updatePlayer({}, {})", player);
        playerValidator.validate(player, result);
        if (result.hasErrors()) {
            return "player";
        } else {
            this.playerService.update(player);
            return "redirect:/players";
        }
    }

    /**
     * Delete player.
     *
     * @return view name
     */
    @GetMapping(value = "/player/{id}/delete")
    public final String deletePlayerById(@PathVariable Integer id, Model model) {
        logger.debug("delete({},{})", id, model);
        playerService.delete(id);
        return "redirect:/players";
    }
}