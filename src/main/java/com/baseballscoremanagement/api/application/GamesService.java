package com.baseballscoremanagement.api.application;


import com.baseballscoremanagement.api.domain.model.Game;

import java.util.List;

public interface GamesService {

  /**
   * 指定したチームの試合一覧取得
   * @param teamId チームID
   * @param start 取得開始位置
   * @param results 取得数
   * @return 試合一覧
   */
  List<Game> getGameListByTeamId(final int teamId, final int start, final int results);
}
