package com.baseballscoremanagement.api.infrastracture.response;

import org.springframework.data.relational.core.mapping.Column;

public class TotalScoreResponse {
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
