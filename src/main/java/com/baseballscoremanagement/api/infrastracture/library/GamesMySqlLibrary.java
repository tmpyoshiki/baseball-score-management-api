package com.baseballscoremanagement.api.infrastracture.library;

import com.baseballscoremanagement.api.infrastracture.response.GameResponse;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface GamesMySqlLibrary  extends ReactiveCrudRepository<GameResponse, Integer> {

  /**
   * 指定チームの試合情報取得
   * @param teamId 試合情報を取得するチームのID
   * @param start 開始位置（0はじまり）
   * @param results 取得数
   * @return {@link GameResponse}
   */
  @Query("SELECT" +
      "  GAMES.ID," +
      "  FIRST_TEAM_ID," +
      "  FIRST_TEAMS.NAME AS FIRST_TEAM_NAME," +
      "  SECOND_TEAM_ID," +
      "  SECOND_TEAMS.NAME AS SECOND_TEAM_NAME," +
      "  START_DATE_TIME," +
      "  END_DATE_TIME," +
      "  FIELDS_ID," +
      "  FIELDS.NAME AS FIELD_NAME" +
      " FROM GAMES" +
      " INNER JOIN TEAMS AS FIRST_TEAMS" +
      " ON GAMES.FIRST_TEAM_ID=FIRST_TEAMS.ID" +
      " INNER JOIN TEAMS AS SECOND_TEAMS" +
      " ON GAMES.SECOND_TEAM_ID=SECOND_TEAMS.ID" +
      " INNER JOIN FIELDS" +
      " ON GAMES.FIELDS_ID=FIELDS.ID" +
      " WHERE FIRST_TEAM_ID=:teamId OR SECOND_TEAM_ID=:teamId" +
      " ORDER BY START_DATE_TIME DESC" +
      " LIMIT :results OFFSET :start;")
  Flux<GameResponse> findGamesByTeamId(int teamId, int start, int results);
}

