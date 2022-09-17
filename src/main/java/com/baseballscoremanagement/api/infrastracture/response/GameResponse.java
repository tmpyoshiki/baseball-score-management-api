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
  @Column("bat_first_team_id")
  private int batFirstTeamId;
  /**
   * 先攻チーム名
   */
  @Column("bat_first_team_name")
  private final String batFirstTeamName;
  /**
   * 後攻チームのID
   */
  @Column("field_first_team_id")
  private int fieldFirstTeamId;
  /**
   * 後攻チーム名
   */
  @Column("field_first_team_name")
  private final String fieldFirstTeamName;
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

