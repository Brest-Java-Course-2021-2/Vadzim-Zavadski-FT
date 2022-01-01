package com.zavadski.web_app;

import com.zavadski.model.Team;
import com.zavadski.service.TeamDtoService;
import com.zavadski.service.TeamService;
import com.zavadski.web_app.validators.TeamValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TeamController {

    private static final Logger logger = LoggerFactory.getLogger(TeamController.class);

    private final TeamDtoService teamDtoService;

    private final TeamService teamService;

    private final TeamValidator teamValidator;

    public TeamController(TeamDtoService teamDtoService,
                          TeamService teamService,
                          TeamValidator teamValidator) {
        this.teamDtoService = teamDtoService;
        this.teamService = teamService;
        this.teamValidator = teamValidator;
    }

    /**
     * Goto teams list page.
     *
     * @return view name
     */
    @GetMapping(value = "/teams")
    public final String teams(Model model) {
        model.addAttribute("teams", teamDtoService.findAllWithNumberOfPlayers());
        model.addAttribute("count", teamService.count());
        return "teams";
    }

    /**
     * Goto edit team page.
     *
     * @return view name
     */
    @GetMapping(value = "/team/{id}")
    public final String gotoEditTeamPage(@PathVariable Integer id, Model model) {
        logger.debug("gotoEditTeamPage(id:{},model:{})", id, model);
        model.addAttribute("isNew", false);
        model.addAttribute("team", teamService.getTeamById(id));
        return "team";
    }

    /**
     * Goto new team page.
     *
     * @return view name
     */
    @GetMapping(value = "/team")
    public final String gotoAddTeamPage(Model model) {
        logger.debug("gotoAddTeamPage({})", model);
        model.addAttribute("isNew", true);
        model.addAttribute("team", new Team());
        return "team";
    }

    /**
     * Persist new team into persistence storage.
     *
     * @param team new team with filled data.
     * @return view name
     */
    @PostMapping(value = "/team")
    public String addTeam(Team team, BindingResult result) {
        logger.debug("addTeam({}, {})", team);
        teamValidator.validate(team, result);
        if (result.hasErrors()) {
            return "team";
        }
        this.teamService.create(team);
        return "redirect:/teams";
    }

    /**
     * Update team.
     *
     * @param team team with filled data.
     * @return view name
     */
    @PostMapping(value = "/team/{id}")
    public String updateTeam(Team team, BindingResult result) {
        logger.debug("updateTeam({}, {})", team);
        teamValidator.validate(team, result);
        if (result.hasErrors()) {
            return "team";
        }
        this.teamService.update(team);
        return "redirect:/teams";
    }

    /**
     * Delete team.
     *
     * @return view name
     */
    @GetMapping(value = "/team/{id}/delete")
    public final String deleteTeamById(
            @PathVariable Integer id,
            Model model,
            RedirectAttributes redirectAttributes) {
        logger.debug("delete({},{})", id, model);
        teamService.isTeamWithPlayers(id);
        if (teamService.isTeamWithPlayers(id)) {
            redirectAttributes.addAttribute("errorMessage",
                    "We're sorry, but we can't delete");
            return "redirect:/error";
        } else {
            teamService.delete(id);
            return "redirect:/teams";
        }
    }
}
