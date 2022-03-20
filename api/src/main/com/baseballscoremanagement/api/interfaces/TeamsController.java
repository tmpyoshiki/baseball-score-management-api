package main.com.baseballscoremanagement.api.interfaces;

import main.com.baseballscoremanagement.api.domain.TeamSort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1")
public class TeamsController {
   private final TeamsService teamsService;

  public TeamsController(final TeamsService teamsService) {
    this.teamsService = teamsService;
  }

  @GetMapping("/teams")
  // とりあえずStringで
  public String getTeams(
    @RequestParam(value = "sort", required = false, defaultValue = "desc_games") TeamSort sort,
    @RequestParam(value = "start", required = false, defaultValue = "0") int start,
    @RequestParam(value = "results", required = false, defaultValue = "3") int results
  ){
    return this.teamsService.getTeams(sort, start, results);
  }
}
