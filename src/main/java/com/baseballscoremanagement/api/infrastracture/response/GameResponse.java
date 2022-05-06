package com.baseballscoremanagement.api.infrastracture.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;

/**
 * MySQLから取得したチームを格納するDTO
 */
@Getter
@AllArgsConstructor
public class GameResponse {
  /**
   * 試合ID
   */
  @Id
  private int id;
  /**
   * 先攻チームのID
   */
  @Column("first_team_id")
  private int firstTeamId;
  /**
   * 先攻チーム名
   */
  @Column("first_team_name")
  private final String firstTeamName;
  /**
   * 後攻チームのID
   */
  @Column("second_team_id")
  private int secondTeamId;
  /**
   * 後攻チーム名
   */
  @Column("second_team_name")
  private final String secondTeamName;
  /**
   * 試合開始時刻
   */
  @Column("start_date_time")
  private final LocalDateTime startDateTime;
  /**
   * 試合終了時刻
   */
  @Column("end_date_time")
  private final LocalDateTime endDateTime;
  /**
   * 球場のID
   */
  @Column("field_id")
  private int fieldId;
  /**
   * 球場名
   */
  @Column("field_name")
  private final String fieldName;
}

