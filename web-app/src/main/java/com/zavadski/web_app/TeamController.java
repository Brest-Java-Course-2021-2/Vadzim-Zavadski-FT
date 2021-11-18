package com.zavadski.web_app;

import com.zavadski.model.Team;
import com.zavadski.service.TeamDtoService;
import com.zavadski.service.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TeamController {

    private static final Logger logger = LoggerFactory.getLogger(TeamController.class);

    private final TeamDtoService teamDtoService;

    private final TeamService teamService;

    public TeamController(TeamDtoService teamDtoService,
                          TeamService teamService) {
        this.teamDtoService = teamDtoService;
        this.teamService = teamService;
    }

    /**
     * Goto teams list page.
     *
     * @return view name
     */
    @GetMapping(value = "/teams")
    public final String teams(Model model) {
        model.addAttribute("teams", teamDtoService.findAllWithNumberOfPlayers());
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
        model.addAttribute("department", teamService.getTeamById(id));
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
    public String addTeam(Team team) {

        logger.debug("addTeam({}, {})", team);
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
    public String updateTeam(Team team) {

        logger.debug("updateTeam({}, {})", team);
        this.teamService.update(team);
        return "redirect:/teams";
    }
}