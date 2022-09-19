package com.baseballscoremanagement.api.interfaces.v1.response.game;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TotalScoreResponse {
  /**
   * 先攻チームのスコア
   */
  @JsonProperty("bat_first_team_score")
  private int batFirstTeamScore;

  /**
   * 後攻チームのスコア
   */
  @JsonProperty("field_first_team_score")
  private int fieldFirstTeamScore;
}
