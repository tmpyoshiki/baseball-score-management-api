package com.baseballscoremanagement.api.interfaces.v1;

import com.baseballscoremanagement.api.application.GamesService;
import com.baseballscoremanagement.api.application.TeamsService;
import com.baseballscoremanagement.api.domain.sort.TeamSort;
import com.baseballscoremanagement.api.interfaces.v1.response.game.GameListResponse;
import com.baseballscoremanagement.api.interfaces.v1.response.TeamListResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1")
public class TeamsController {
   private final TeamsService teamsService;
   private final GamesService gamesService;

  /**
   * コンストラクタ
   * @param teamsService　チーム関連のサービス
   * @param gamesService　試合関連のサービス
   */
  public TeamsController(final TeamsService teamsService, final GamesService gamesService) {
    this.teamsService = teamsService;
    this.gamesService = gamesService;
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

  /**
   * 指定チームの試合一覧取得
   * @param teamId　チームIDの
   * @param start 取得開始位置
   * @param results 取得数
   * @return 試合一覧
   */
  @GetMapping("/teams/{teamId}/games")
  public GameListResponse getGameListByTeamId(
      @PathVariable("teamId") int teamId,
      @RequestParam(value = "start", defaultValue = "0") int start,
      @RequestParam(value = "results", defaultValue = "3") int results
  ){
    final var gameList = this.gamesService.getGameListByTeamId(teamId, start, results);
    return new GameListResponse(gameList);
  }
}
