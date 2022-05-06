package com.baseballscoremanagement.api.infrastracture.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.sql.Date;

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
  private int first_team_id;
  /**
   * 先攻チーム名
   */
  private String first_team_name;
  /**
   * 後攻チームのID
   */
  private int second_team_id;
  /**
   * 後攻チーム名
   */
  private String second_team_name;
  /**
   * 試合開始時刻
   */
  private Date start_date_time;
  /**
   * 試合終了時刻
   */
  private Date end_date_time;
  /**
   * 球場のID
   */
  private int field_id;
  /**
   * 球場名
   */
  private String field_name;
}

