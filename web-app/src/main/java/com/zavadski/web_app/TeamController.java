package com.zavadski.web_app;

import com.zavadski.service.TeamDtoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TeamController {

    private final TeamDtoService teamDtoService;

    public TeamController(TeamDtoService teamDtoService) {
        this.teamDtoService = teamDtoService;
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
        return "team";
    }

    /**
     * Goto new team page.
     *
     * @return view name
     */
    @GetMapping(value = "/team/add")
    public final String gotoAddTeamPage(Model model) {
//        model.addAttribute("departments", teamDtoService.findAllWithNumberOfPlayers());
        return "team";
    }
}