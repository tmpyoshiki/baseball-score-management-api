package com.baseballscoremanagement.api.application.impl;

import com.baseballscoremanagement.api.application.TeamsService;
import com.baseballscoremanagement.api.domain.model.Team;
import com.baseballscoremanagement.api.domain.repository.TeamsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamsServiceImpl implements TeamsService {
  private final TeamsRepository teamsRepository;

  public TeamsServiceImpl(final TeamsRepository teamsRepository) {
    this.teamsRepository = teamsRepository;
  }

  @Override
  public List<Team> getTeamList(final int start, final int results) {
    return this.teamsRepository.getTeamList( start, results).collectList().block();
  }
}
