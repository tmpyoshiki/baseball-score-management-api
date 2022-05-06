package com.baseballscoremanagement.api.infrastracture.repository;

import com.baseballscoremanagement.api.domain.model.Field;
import com.baseballscoremanagement.api.domain.model.Game;
import com.baseballscoremanagement.api.domain.model.Team;
import com.baseballscoremanagement.api.domain.repository.GamesRepository;
import com.baseballscoremanagement.api.domain.repository.TeamsRepository;
import com.baseballscoremanagement.api.domain.sort.TeamSort;
import com.baseballscoremanagement.api.infrastracture.library.GamesMySqlLibrary;
import com.baseballscoremanagement.api.infrastracture.library.TeamsMySqlLibrary;
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
    return gameListResponse.map(this::storeGameInfo);
  }

  /**
   * 試合情報をmodelに格納する
   * @param gameResponse DBから取得した試合情報
   * @return {@link Game}
   */
  private Game storeGameInfo (final GameResponse gameResponse) {
    final var gameId = gameResponse.getId();
    final var firstTeam = new Team(gameResponse.getFirstTeamId(), gameResponse.getFirstTeamName());
    final var secondTeam = new Team(gameResponse.getSecondTeamId()  , gameResponse.getSecondTeamName());
    final var field = new Field(gameResponse.getFieldId(), gameResponse.getFieldName());
    final var startDateTime = gameResponse.getStartDateTime();
    final var endDateTime = gameResponse.getEndDateTime();
    return new Game(gameId, firstTeam, secondTeam, field, startDateTime, endDateTime);
  }
}
