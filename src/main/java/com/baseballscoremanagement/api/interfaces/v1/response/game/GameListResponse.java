package com.baseballscoremanagement.api.interfaces.v1.response.game;

import com.baseballscoremanagement.api.domain.model.Game;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 試合一覧取得で返すレスポンス用のDTO
 */
public class GameListResponse {
  /**
   * 試合一覧
   */
  @JsonProperty("game_list")
  private final List<GameResponse> gameListResponse;

  public GameListResponse(final List<Game> gameList) {
    this.gameListResponse = gameList.stream().map(GameResponse::new).collect(Collectors.toList());
  }
}
