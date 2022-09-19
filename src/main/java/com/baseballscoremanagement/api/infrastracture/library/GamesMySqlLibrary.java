package com.baseballscoremanagement.api.infrastracture.library;

import com.baseballscoremanagement.api.infrastracture.response.GameResponse;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface GamesMySqlLibrary extends ReactiveCrudRepository<GameResponse, Integer> {

  /**
   * 指定チームの試合情報取得
   * @param teamId 試合情報を取得するチームのID
   * @param offset 開始位置（0はじまり）
   * @param limit 取得数
   * @return {@link GameResponse}
   */
  @Query("SELECT " +
      "  GAMES.ID, " +
      "  BAT_FIRST_TEAM_ID, " +
      "  BAT_FIRST_TEAMS.NAME AS BAT_FIRST_TEAM_NAME, " +
      "  BAT_FIRST_SCORES.TOTAL_SCORE AS BAT_FIRST_TEAM_SCORE, " +
      "  FIELD_FIRST_TEAM_ID, " +
      "  FIELD_FIRST_TEAMS.NAME AS FIELD_FIRST_TEAM_NAME, " +
      "  FIELD_FIRST_SCORES.TOTAL_SCORE AS FIELD_FIRST_TEAM_SCORE, " +
      "  START_DATE_TIME, " +
      "  END_DATE_TIME, " +
      "  FIELD_ID, " +
      "  FIELDS.NAME AS FIELD_NAME " +
      "FROM GAMES " +
      "INNER JOIN " +
      "  (" +
      "    SELECT " +
      "      GAME_ID, " +
      "      IS_TOP_OF_INNING, " +
      "      SUM(SCORE) AS TOTAL_SCORE " +
      "    FROM SCORES " +
      "    GROUP BY GAME_ID,IS_TOP_OF_INNING " +
      "  ) BAT_FIRST_SCORES " +
      "ON GAMES.ID=BAT_FIRST_SCORES.GAME_ID AND BAT_FIRST_SCORES.IS_TOP_OF_INNING=TRUE " +
      "INNER JOIN " +
      "  (" +
      "    SELECT " +
      "      GAME_ID, " +
      "      IS_TOP_OF_INNING, " +
      "      SUM(SCORE) AS TOTAL_SCORE " +
      "    FROM SCORES " +
      "    GROUP BY GAME_ID,IS_TOP_OF_INNING " +
      "  ) FIELD_FIRST_SCORES " +
      "ON GAMES.ID=FIELD_FIRST_SCORES.GAME_ID AND FIELD_FIRST_SCORES.IS_TOP_OF_INNING=FALSE " +
      "INNER JOIN TEAMS AS BAT_FIRST_TEAMS " +
      "ON GAMES.BAT_FIRST_TEAM_ID=BAT_FIRST_TEAMS.ID " +
      "INNER JOIN TEAMS AS FIELD_FIRST_TEAMS " +
      "ON GAMES.FIELD_FIRST_TEAM_ID=FIELD_FIRST_TEAMS.ID " +
      "INNER JOIN FIELDS " +
      "ON GAMES.FIELD_ID=FIELDS.ID " +
      "WHERE BAT_FIRST_TEAM_ID=:teamId OR FIELD_FIRST_TEAM_ID=:teamId " +
      "ORDER BY START_DATE_TIME DESC " +
      "LIMIT :limit OFFSET :offset;")
  Flux<GameResponse> findGamesByTeamId(int teamId, int offset, int limit);
}

