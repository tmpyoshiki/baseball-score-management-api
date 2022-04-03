package com.baseballscoremanagement.api.interfaces.v1.response;

import com.baseballscoremanagement.api.domain.model.Team;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.stream.Collectors;

/**
 * チーム一覧取得で返すレスポンス用のDTO
 */
public class TeamListResponse {

  /**
   * チーム一覧
   */
  @JsonProperty("team_list")
  private final List<TeamResponse> teamListResponse;

  public TeamListResponse(final List<Team> teamListResponse) {
    this.teamListResponse = teamListResponse.stream().map(team -> new TeamResponse(team.getId(), team.getName())).collect(Collectors.toList());
  }
}
