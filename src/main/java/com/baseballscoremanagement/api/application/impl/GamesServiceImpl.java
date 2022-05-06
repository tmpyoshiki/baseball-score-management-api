package com.baseballscoremanagement.api.application.impl;

import com.baseballscoremanagement.api.application.GamesService;
import com.baseballscoremanagement.api.domain.model.Game;
import com.baseballscoremanagement.api.domain.repository.GamesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GamesServiceImpl implements GamesService {
  private final GamesRepository gamesRepository;

  public GamesServiceImpl(final GamesRepository gamesRepository) {
    this.gamesRepository = gamesRepository;
  }

  @Override
  public List<Game> getGameListByTeamId(int teamId, int start, int results) {
    return this.gamesRepository.getGameListByTeamId(teamId, start, results).collectList().block();
  }
}
