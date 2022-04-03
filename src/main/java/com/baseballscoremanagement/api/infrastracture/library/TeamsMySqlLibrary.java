package com.baseballscoremanagement.api.infrastracture.library;

import com.baseballscoremanagement.api.infrastracture.response.TeamResponse;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TeamsMySqlLibrary extends ReactiveCrudRepository<TeamResponse, Integer> {

  /**
   * 指定件数のチームを取得
   * @param start 開始位置（0はじまり）
   * @param results 取得数
   * @return {@link TeamResponse}
   */
  @Query("SELECT * FROM TEAMS LIMIT :results OFFSET :start")
  Flux<TeamResponse> findTeams(int start, int results);
}
