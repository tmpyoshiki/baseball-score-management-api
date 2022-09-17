package com.baseballscoremanagement.api.infrastracture.library;

import com.baseballscoremanagement.api.infrastracture.response.TotalScoreResponse;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ScoresMySqlLibrary extends ReactiveCrudRepository<TotalScoreResponse, Integer> {

  /**
   * 指定試合の合計得点を取得
   * @param gameId 試合ID
   * @return {@link TotalScoreResponse}
   */
  @Query("SELECT " +
      "IS_TOP_OF_INNING, " +
      "SUM(SCORE) AS TOTAL_SCORE " +
      "FROM SCORES " +
      "WHERE GAME_ID=2 " +
      "GROUP BY GAME_ID,IS_TOP_OF_INNING;")
  Flux<TotalScoreResponse> findTotalScoreByGameId(int gameId);
}
