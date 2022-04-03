package com.baseballscoremanagement.api.infrastracture.repository;

import com.baseballscoremanagement.api.domain.model.Team;
import com.baseballscoremanagement.api.domain.repository.TeamsRepository;
import com.baseballscoremanagement.api.domain.sort.TeamSort;
import com.baseballscoremanagement.api.infrastracture.library.TeamsMySqlLibrary;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public class TeamsRepositoryImpl implements TeamsRepository {
  private final TeamsMySqlLibrary teamsMySqlLibrary;

  public TeamsRepositoryImpl(final TeamsMySqlLibrary teamsMySqlLibrary) {
    this.teamsMySqlLibrary = teamsMySqlLibrary;
  }

  @Override
  public Flux<Team> getTeamList(final TeamSort teamSort, int start, int results) {
    // TODO: sortも反映させる
    final var response = this.teamsMySqlLibrary.findTeams(start, results);
    return response.map(res -> new Team(res.getId(), res.getName()));
  }
}
