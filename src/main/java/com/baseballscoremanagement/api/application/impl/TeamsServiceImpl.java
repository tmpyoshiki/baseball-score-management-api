package com.baseballscoremanagement.api.application.impl;

import com.baseballscoremanagement.api.application.TeamsService;
import com.baseballscoremanagement.api.domain.model.Team;
import com.baseballscoremanagement.api.domain.repository.TeamsRepository;
import com.baseballscoremanagement.api.domain.sort.TeamSort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamsServiceImpl implements TeamsService {
  private final TeamsRepository teamsRepository;

  public TeamsServiceImpl(final TeamsRepository teamsRepository) {
    this.teamsRepository = teamsRepository;
  }

  @Override
  public List<Team> getTeamList(final TeamSort sort, final int start, final int results) {
    return this.teamsRepository.getTeamList(sort, start, results).collectList().block();
  }
}
