package com.baseballscoremanagement.api.domain.repository;

import com.baseballscoremanagement.api.domain.model.Game;
import reactor.core.publisher.Flux;

public interface GamesRepository {
  /**
   * 指定チームの試合情報を取得
   * @param teamId チームID
   * @param start 取得開始位置
   * @param results 取得数
   * @return {@link Game}
   */
  Flux<Game> getGameListByTeamId(int teamId, int start, int results);
}
