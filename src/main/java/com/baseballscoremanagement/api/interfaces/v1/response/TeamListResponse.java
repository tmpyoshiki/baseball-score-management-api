package com.baseballscoremanagement.api.interfaces.v1.response;

import com.baseballscoremanagement.api.domain.model.Team;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeamListResponse {

  private final List<TeamResponse> teamListResponse;

  public TeamListResponse(final List<Team> teamListResponse) {
    this.teamListResponse = teamListResponse.stream().map(team -> new TeamResponse(team.getId(), team.getName())).collect(Collectors.toList());
  }
}
