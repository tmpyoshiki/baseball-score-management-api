package com.baseballscoremanagement.api.interfaces.v1.response.game;

import com.baseballscoremanagement.api.domain.model.Field;
import com.baseballscoremanagement.api.domain.model.Game;
import com.baseballscoremanagement.api.domain.model.Team;
import com.baseballscoremanagement.api.domain.model.TotalScore;
import com.baseballscoremanagement.api.interfaces.v1.response.team.TeamResponse;
import com.baseballscoremanagement.api.interfaces.v1.response.field.FieldResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

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
  @JsonProperty("bat_first_team")
  private final TeamResponse batFirstTeam;

  /**
   * 後攻チーム情報
   */
  @JsonProperty("field_first_team")
  private final TeamResponse fieldFirstTeam;

  /**
   * 開催球場情報
   */
  @JsonProperty("field")
  private final FieldResponse field;

  /**
   * 試合開始時刻
   */
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
  @JsonProperty("start_date_time")
  private final LocalDateTime startDateTime;

  /**
   * 試合終了時刻
   */
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
  @JsonProperty("end_date_time")
  private final LocalDateTime endDateTime;

  /**
   * スコア情報
   */
  @JsonProperty("total_score")
  private final TotalScoreResponse totalScore;

  public GameResponse(final Game game) {
    this.id = game.getId();
    this.batFirstTeam = this.setTeamResponse(game.getBatFirstTeam());
    this.fieldFirstTeam = this.setTeamResponse(game.getFieldFirstTeam());
    this.field = this.setFieldResponse(game.getField());
    this.startDateTime = game.getStartDateTime();
    this.endDateTime = game.getEndDateTime();
    this.totalScore = this.setTotalScoreResponse(game.getScore());
  }

  private TeamResponse setTeamResponse(final Team team) {
    return new TeamResponse(team.getId(), team.getName());
  }

  private FieldResponse setFieldResponse(final Field field) {
    return new FieldResponse(field.getId(), field.getName());
  }

  private TotalScoreResponse setTotalScoreResponse(final TotalScore totalScore) {
    return new TotalScoreResponse(totalScore.getBatFirstTeamScore(), totalScore.getFieldFirstTeamScore());
  }
}
