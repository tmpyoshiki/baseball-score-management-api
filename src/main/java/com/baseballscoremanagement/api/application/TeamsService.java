package com.baseballscoremanagement.api.application;


import com.baseballscoremanagement.api.domain.model.Team;
import com.baseballscoremanagement.api.domain.sort.TeamSort;

import java.util.List;

public interface TeamsService {

  /**
   * チーム一覧取得
   * @param sort ソート順
   * @param start 取得開始位置
   * @param results 取得数
   * @return チーム一覧
   */
  List<Team> getTeamList(final TeamSort sort, final int start, final int results);
}
