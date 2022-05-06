package com.baseballscoremanagement.api.infrastracture.repository;

import com.baseballscoremanagement.api.domain.model.Team;
import com.baseballscoremanagement.api.domain.repository.TeamsRepository;
import com.baseballscoremanagement.api.infrastracture.library.TeamsMySqlLibrary;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class TeamsRepositoryImpl implements TeamsRepository {
  private final TeamsMySqlLibrary teamsMySqlLibrary;

  public TeamsRepositoryImpl(final TeamsMySqlLibrary teamsMySqlLibrary) {
    this.teamsMySqlLibrary = teamsMySqlLibrary;
  }

  @Override
  public Flux<Team> getTeamList(int start, int results) {
    final var response = this.teamsMySqlLibrary.findTeams(start, results);
    return response.map(res -> new Team(res.getId(), res.getName()));
  }
}
