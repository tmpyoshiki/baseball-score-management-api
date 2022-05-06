package com.baseballscoremanagement.api.application;


import com.baseballscoremanagement.api.domain.model.Team;

import java.util.List;

public interface TeamsService {

  /**
   * チーム一覧取得
   * @param start 取得開始位置
   * @param results 取得数
   * @return チーム一覧
   */
  List<Team> getTeamList(final int start, final int results);
}
