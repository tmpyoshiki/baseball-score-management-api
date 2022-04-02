package com.baseballscoremanagement.api.interfaces.v1;

import com.baseballscoremanagement.api.application.TeamsService;
import com.baseballscoremanagement.api.domain.model.Team;
import com.baseballscoremanagement.api.domain.sort.TeamSort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1")
public class TeamsController {
   private final TeamsService teamsService;

  /**
   * コンストラクタ
   * @param teamsService　チームサービス
   */
  public TeamsController(final TeamsService teamsService) {
    this.teamsService = teamsService;
  }

  /**
   * チーム一覧取得
   * @param sort　ソート順
   * @param start 取得開始位置
   * @param results 取得数
   * @return チーム一覧
   */
  @GetMapping("/teams")
  public List<Team> getTeamList(
    @RequestParam(value = "sort", required = false, defaultValue = "desc_games") final TeamSort sort,
    @RequestParam(value = "start", required = false, defaultValue = "1") final int start,
    @RequestParam(value = "results", required = false, defaultValue = "3") final int results
  ){
    return this.teamsService.getTeamList(sort, start, results);
  }
}
