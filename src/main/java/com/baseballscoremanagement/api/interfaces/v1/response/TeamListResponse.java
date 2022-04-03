package com.baseballscoremanagement.api.interfaces.v1.response;

import com.baseballscoremanagement.api.domain.model.Team;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.stream.Collectors;

public class TeamListResponse {

  @JsonProperty("team_list")
  private final List<TeamResponse> teamListResponse;

  public TeamListResponse(final List<Team> teamListResponse) {
    this.teamListResponse = teamListResponse.stream().map(team -> new TeamResponse(team.getId(), team.getName())).collect(Collectors.toList());
  }
}