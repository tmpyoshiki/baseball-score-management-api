package com.baseballscoremanagement.api.application;


import com.baseballscoremanagement.api.domain.sort.TeamSort;

public interface TeamsService {

  /**
   * チーム一覧取得
   * @param sort ソート順
   * @param start 取得開始位置
   * @param results 取得数
   * @return TODO: とりあえずstringにしているがあとでちゃんと定義
   */
  String getTeams(final TeamSort sort, final int start, final int results);
}
