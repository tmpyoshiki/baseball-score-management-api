package com.baseballscoremanagement.api.domain.repository;

import com.baseballscoremanagement.api.domain.model.Team;
import reactor.core.publisher.Flux;

public interface TeamsRepository {
  /**
   * 指定件数のチームを取得
   * @param start 取得開始位置
   * @param results 取得数
   * @return {@link Team}
   */
  Flux<Team> getTeamList(int start, int results);
}
