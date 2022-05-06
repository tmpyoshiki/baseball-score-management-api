package com.baseballscoremanagement.api.helper.game;

import com.baseballscoremanagement.api.domain.model.Field;
import com.baseballscoremanagement.api.domain.model.Game;
import com.baseballscoremanagement.api.domain.model.Team;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GameCreator {
  /**
   * テスト用のGameを作成する
   * @param id 試合IDなどテストデータに使うID(1以上を指定)
   * @return {@link Game}
   */
  public static Game createGame (final int id) {
    final var firstTeam = new Team(1, "テストチーム1");
    final var secondTeam = new Team(id + 1, "テストチーム2");
    final var field = new Field(id, "テスト球場");
    final var startDateTime = LocalDateTime.of(2000, 1,1,12,0);
    final var endDateTime = LocalDateTime.of(2000, 1,1,14,0);
    return new Game(id, firstTeam, secondTeam, field, startDateTime, endDateTime);
  }

  /**
   * テスト用のGameのリストを作成する
   * @param length リストの長さ
   * @return Gameのリスト
   */
  public static List<Game> createGameList (final int length) {
    return IntStream.range(0,2)
        .mapToObj(GameCreator::createGame)
        .collect(Collectors.toList());
  }
}
