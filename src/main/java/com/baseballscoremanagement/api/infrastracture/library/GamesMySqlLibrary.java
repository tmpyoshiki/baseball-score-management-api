package com.baseballscoremanagement.api.infrastracture.library;

import com.baseballscoremanagement.api.infrastracture.response.GameResponse;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface GamesMySqlLibrary extends ReactiveCrudRepository<GameResponse, Integer> {

  /**
   * 指定チームの試合情報取得
   * @param teamId 試合情報を取得するチームのID
   * @param start 開始位置（0はじまり）
   * @param results 取得数
   * @return {@link GameResponse}
   */
  @Query("SELECT" +
      "  GAMES.ID," +
      "  BAT_FIRST_TEAM_ID," +
      "  BAT_FIRST_TEAMS.NAME AS BAT_FIRST_TEAM_NAME," +
      "  FIELD_FIRST_TEAM_ID," +
      "  FIELD_FIRST_TEAMS.NAME AS FIELD_FIRST_TEAM_NAME," +
      "  START_DATE_TIME," +
      "  END_DATE_TIME," +
      "  FIELD_ID," +
      "  FIELDS.NAME AS FIELD_NAME" +
      " FROM GAMES" +
      " INNER JOIN TEAMS AS BAT_FIRST_TEAMS" +
      " ON GAMES.BAT_FIRST_TEAM_ID=BAT_FIRST_TEAMS.ID" +
      " INNER JOIN TEAMS AS FIELD_FIRST_TEAMS" +
      " ON GAMES.FIELD_FIRST_TEAM_ID=FIELD_FIRST_TEAMS.ID" +
      " INNER JOIN FIELDS" +
      " ON GAMES.FIELD_ID=FIELDS.ID" +
      " WHERE BAT_FIRST_TEAM_ID=:teamId OR FIELD_FIRST_TEAM_ID=:teamId" +
      " ORDER BY START_DATE_TIME DESC" +
      " LIMIT :results OFFSET :start;")
  Flux<GameResponse> findGamesByTeamId(int teamId, int start, int results);
}

