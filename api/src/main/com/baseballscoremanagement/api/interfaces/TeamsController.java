package main.com.baseballscoremanagement.api.interfaces;

import main.com.baseballscoremanagement.api.application.TeamsService;
import main.com.baseballscoremanagement.api.domain.TeamSort;
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
   * @return TODO: とりあえずstringにしているがあとでちゃんと定義
   */
  @GetMapping("/teams")
  public String getTeams(
    @RequestParam(value = "sort", required = false, defaultValue = "desc_games") TeamSort sort,
    @RequestParam(value = "start", required = false, defaultValue = "0") int start,
    @RequestParam(value = "results", required = false, defaultValue = "3") int results
  ){
    return this.teamsService.getTeams(sort, start, results);
  }
}
