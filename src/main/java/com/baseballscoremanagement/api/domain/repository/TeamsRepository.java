package com.baseballscoremanagement.api.domain.repository;

import com.baseballscoremanagement.api.domain.model.Team;
import com.baseballscoremanagement.api.domain.sort.TeamSort;
import reactor.core.publisher.Flux;

public interface TeamsRepository {
  Flux<Team> getTeamList(final TeamSort teamSort, int start, int results);
}
