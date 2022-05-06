package com.baseballscoremanagement.api.interfaces.v1.response.game;

import com.baseballscoremanagement.api.domain.model.Field;
import com.baseballscoremanagement.api.domain.model.Game;
import com.baseballscoremanagement.api.domain.model.Team;
import com.baseballscoremanagement.api.interfaces.v1.response.TeamResponse;
import com.baseballscoremanagement.api.interfaces.v1.response.field.FieldResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

/**
 * 試合情報レスポンス用のDTO
 */
public class GameResponse {
  /**
   * 試合ID
   */
  @JsonProperty("id")
  private int id;

  /**
   * 先攻チーム情報
   */
  @JsonProperty("first_team")
  private final TeamResponse firstTeam;

  /**
   * 後攻チーム情報
   */
  @JsonProperty("second_team")
  private final TeamResponse secondTeam;

  /**
   * 開催球場情報
   */
  @JsonProperty("field")
  private final FieldResponse field;

  /**
   * 試合開始時刻
   */
  @JsonProperty("start_date_time")
  private final LocalDateTime startDateTime;

  /**
   * 試合終了時刻
   */
  @JsonProperty("end_date_time")
  private final LocalDateTime endDateTime;

  public GameResponse(final Game game) {
    this.id = game.getId();
    this.firstTeam = this.storeTeamResponse(game.getFirstTeam());
    this.secondTeam = this.storeTeamResponse(game.getSecondTeam());
    this.field = this.storeFieldResponse(game.getField());
    this.startDateTime = game.getStartDateTime();
    this.endDateTime = game.getEndDateTime();
  }

  private TeamResponse storeTeamResponse(final Team team) {
    return new TeamResponse(team.getId(), team.getName());
  }

  private FieldResponse storeFieldResponse(final Field field) {
    return new FieldResponse(field.getId(), field.getName());
  }
}
