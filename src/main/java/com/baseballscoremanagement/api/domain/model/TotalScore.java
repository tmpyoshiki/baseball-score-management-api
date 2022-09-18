package com.baseballscoremanagement.api.domain.model;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 合計のスコアを格納するDTO
 */
@AllArgsConstructor
@Getter
public class TotalScore {
  /**
   * 先攻チームのスコア
   */
  private int batFirstTeamScore;
  /**
   * 後攻チームのスコア
   */
  private int fieldFirstTeamScore;
}
