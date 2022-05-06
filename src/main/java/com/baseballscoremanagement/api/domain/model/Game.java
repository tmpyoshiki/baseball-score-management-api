package com.baseballscoremanagement.api.domain.model;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 試合情報を格納するDTO
 */
@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class Game {
  /**
   * 試合ID
   */
  private int id;
  /**
   * 先攻チーム
   */
  private final Team firstTeam;
  /**
   * 後攻チーム
   */
  private final Team secondTeam;
  /**
   * 開催球場
   */
  private final Field field;
  /**
   * 開始時刻
   */
  private final LocalDateTime startDateTime;
  /**
   * 終了時刻
   */
  private final LocalDateTime endDateTime;
}
