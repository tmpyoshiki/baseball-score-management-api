package com.baseballscoremanagement.api.infrastracture.response;

import lombok.Getter;
import org.springframework.data.relational.core.mapping.Column;

@Getter
public class TotalScoreResponse {
  @Column("game_id")
  private int gameId;

  /**
   * 先攻チームかどうか
   */
  @Column("is_top_of_inning")
  private boolean isTopOfInning;
  /**
   * 合計スコア
   */
  @Column("total_score")
  private int totalScore;
}
