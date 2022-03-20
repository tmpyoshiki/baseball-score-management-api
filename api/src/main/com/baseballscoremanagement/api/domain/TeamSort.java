package main.com.baseballscoremanagement.api.domain;

public enum TeamSort {
  DESC_GAMES("desc_games"),
  ASC_GAMES("asc_games");

  private final String sort;

  TeamSort(final String sort) {
    this.sort = sort;
  }
}
