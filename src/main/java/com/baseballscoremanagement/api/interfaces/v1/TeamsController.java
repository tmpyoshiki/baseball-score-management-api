package com.baseballscoremanagement.api.interfaces.v1;

import com.baseballscoremanagement.api.application.TeamsService;
import com.baseballscoremanagement.api.domain.sort.TeamSort;
import com.baseballscoremanagement.api.interfaces.v1.response.TeamListResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
  public TeamListResponse getTeamList(
    @RequestParam(value = "sort", defaultValue = "DESC_GAMES") final String sort,
    @RequestParam(value = "start", defaultValue = "0") final int start,
    @RequestParam(value = "results", defaultValue = "3") final int results
  ){
    final var teamSort = TeamSort.valueOf(sort);
    final var teamList = this.teamsService.getTeamList(teamSort, start, results);
    return new TeamListResponse(teamList);
  }
}
