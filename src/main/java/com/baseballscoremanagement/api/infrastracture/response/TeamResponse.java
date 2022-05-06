package com.baseballscoremanagement.api.infrastracture.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;

/**
 * MySQLから取得したチームを格納するDTO
 */
@Getter
@AllArgsConstructor
public class TeamResponse {
  /**
   * チームID
   */
  @Id
  private int id;
  /**
   * チーム名
   */
  private final String name;
}
