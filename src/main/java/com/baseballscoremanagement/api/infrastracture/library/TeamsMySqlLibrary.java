package com.baseballscoremanagement.api.infrastracture.library;

import com.baseballscoremanagement.api.infrastracture.response.TeamResponse;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface TeamsMySqlLibrary extends ReactiveCrudRepository<TeamResponse, Integer> {

  @Query("SELECT * FROM TEAMS LIMIT :results OFFSET :start")
  Flux<TeamResponse> findTeams(int start, int results);
}