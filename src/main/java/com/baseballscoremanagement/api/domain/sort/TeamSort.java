package com.baseballscoremanagement.api.domain.sort;

/**
 * チームのソート順序
 */
public enum TeamSort {
  /**
   * 試合数降順
   */
  DESC_GAMES("desc_games"),
  /**
   * 試合数昇順
   */
  ASC_GAMES("asc_games");

  private final String sort;

  TeamSort(final String sort) {
    this.sort = sort;
  }
}
