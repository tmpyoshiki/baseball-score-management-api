package com.baseballscoremanagement.api.infrastracture.repository;

import com.baseballscoremanagement.api.domain.model.Field;
import com.baseballscoremanagement.api.domain.model.Game;
import com.baseballscoremanagement.api.domain.model.Team;
import com.baseballscoremanagement.api.domain.model.TotalScore;
import com.baseballscoremanagement.api.domain.repository.GamesRepository;
import com.baseballscoremanagement.api.infrastracture.library.GamesMySqlLibrary;
import com.baseballscoremanagement.api.infrastracture.library.ScoresMySqlLibrary;
import com.baseballscoremanagement.api.infrastracture.response.GameResponse;
import com.baseballscoremanagement.api.infrastracture.response.TotalScoreResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class GamesRepositoryImpl implements GamesRepository {
  private final GamesMySqlLibrary gamesMySqlLibrary;
  private final ScoresMySqlLibrary scoresMySqlLibrary;

  public GamesRepositoryImpl(final GamesMySqlLibrary gamesMySqlLibrary, final ScoresMySqlLibrary scoresMySqlLibrary) {
    this.gamesMySqlLibrary = gamesMySqlLibrary;
    this.scoresMySqlLibrary = scoresMySqlLibrary;
  }

  @Override
  public Flux<Game> getGameListByTeamId(int teamId, int start, int results) {
    final var gameListResponse = this.gamesMySqlLibrary.findGamesByTeamId(teamId, start, results);
    final var gameIdList = Objects.requireNonNull(gameListResponse.map(GameResponse::getId).collectList().block());
    final var totalScoreResponseList = this.scoresMySqlLibrary.findTotalScoreByGameIds(getMultiIdQuery(gameIdList)).collectList().block();
    return gameListResponse.map(gameResponse -> {
      final var batFirstTeamScore = Objects.requireNonNull(totalScoreResponseList).stream().filter(TotalScoreResponse::isTopOfInning).findFirst().get();
      final var fieldFirstTeamScore = Objects.requireNonNull(totalScoreResponseList).stream().filter(totalScoreResponse -> !totalScoreResponse.isTopOfInning()).findFirst().get();
      return this.setGameInfo(gameResponse, batFirstTeamScore.getTotalScore(), fieldFirstTeamScore.getTotalScore());
    });
  }

  /**
   * 試合情報をmodelに格納する
   * @param gameResponse DBから取得した試合情報
   * @return {@link Game}
   */
  private Game setGameInfo (final GameResponse gameResponse, final int batFirstTeamScore, final int fieldFirstTeamScore) {
    final var gameId = gameResponse.getId();
    final var batFirstTeam = new Team(gameResponse.getBatFirstTeamId(), gameResponse.getBatFirstTeamName());
    final var fieldFirstTeam = new Team(gameResponse.getFieldFirstTeamId()  , gameResponse.getFieldFirstTeamName());
    final var field = new Field(gameResponse.getFieldId(), gameResponse.getFieldName());
    final var startDateTime = gameResponse.getStartDateTime();
    final var endDateTime = gameResponse.getEndDateTime();
    final var totalScore = new TotalScore(batFirstTeamScore, fieldFirstTeamScore);
    return new Game(gameId, batFirstTeam, fieldFirstTeam, field, startDateTime, endDateTime, totalScore);
  }

  /**
   *
   * @param idList
   * @return
   */
  private String getMultiIdQuery (final List<Integer> idList) {
    return idList.stream().map(String::valueOf).collect(Collectors.joining(","));
  }
}
