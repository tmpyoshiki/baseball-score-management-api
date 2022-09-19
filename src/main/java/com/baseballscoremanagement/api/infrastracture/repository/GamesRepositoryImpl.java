package com.baseballscoremanagement.api.infrastracture.repository;

import com.baseballscoremanagement.api.domain.model.Field;
import com.baseballscoremanagement.api.domain.model.Game;
import com.baseballscoremanagement.api.domain.model.Team;
import com.baseballscoremanagement.api.domain.model.TotalScore;
import com.baseballscoremanagement.api.domain.repository.GamesRepository;
import com.baseballscoremanagement.api.infrastracture.library.GamesMySqlLibrary;
import com.baseballscoremanagement.api.infrastracture.response.GameResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class GamesRepositoryImpl implements GamesRepository {
  private final GamesMySqlLibrary gamesMySqlLibrary;

  public GamesRepositoryImpl(final GamesMySqlLibrary gamesMySqlLibrary) {
    this.gamesMySqlLibrary = gamesMySqlLibrary;
  }

  @Override
  public Flux<Game> getGameListByTeamId(int teamId, int start, int results) {
    final var gameListResponse = this.gamesMySqlLibrary.findGamesByTeamId(teamId, start, results);
    return gameListResponse.map(this::setGameInfo);
  }

  /**
   * 試合情報をmodelに格納する
   * @param gameResponse DBから取得した試合情報
   * @return {@link Game}
   */
  private Game setGameInfo (final GameResponse gameResponse) {
    final var gameId = gameResponse.getId();
    final var batFirstTeam = new Team(gameResponse.getBatFirstTeamId(), gameResponse.getBatFirstTeamName());
    final var fieldFirstTeam = new Team(gameResponse.getFieldFirstTeamId()  , gameResponse.getFieldFirstTeamName());
    final var totalScore = new TotalScore(gameResponse.getBatFirstTeamScore(), gameResponse.getFieldFirstTeamScore());
    final var field = new Field(gameResponse.getFieldId(), gameResponse.getFieldName());
    final var startDateTime = gameResponse.getStartDateTime();
    final var endDateTime = gameResponse.getEndDateTime();
    return new Game(gameId, batFirstTeam, fieldFirstTeam, field, startDateTime, endDateTime, totalScore);
  }
}
