package com.baseballscoremanagement.api.domain.repository;

import com.baseballscoremanagement.api.domain.model.Team;
import com.baseballscoremanagement.api.domain.sort.TeamSort;
import reactor.core.publisher.Flux;

public interface TeamsRepository {
  /**
   * 指定件数のチームを取得
   * @param teamSort チームのソート順
   * @param start 取得開始位置
   * @param results 取得数
   * @return {@link Team}
   */
  Flux<Team> getTeamList(final TeamSort teamSort, int start, int results);
}
