package com.baseballscoremanagement.api.application.impl;

import com.baseballscoremanagement.api.domain.model.Field;
import com.baseballscoremanagement.api.domain.model.Game;
import com.baseballscoremanagement.api.domain.model.Team;
import com.baseballscoremanagement.api.domain.repository.GamesRepository;
import com.baseballscoremanagement.api.domain.repository.TeamsRepository;
import com.baseballscoremanagement.api.domain.sort.TeamSort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class GamesServiceImplTest {

  @Mock
  private GamesRepository gamesRepository;

  @InjectMocks
  private GamesServiceImpl gamesServiceImpl;

  @Nested
  class getGameListByTeamId {
    @Test
    void 取得した結果を正常に返すことができること() {
      final Flux<Game> actualGameFlux = Flux.range(0,2)
          .map(GamesServiceImplTest.this::createGame);
      final List<Game> expectGameList = IntStream.range(0,2)
          .mapToObj(GamesServiceImplTest.this::createGame)
          .collect(Collectors.toList());
          createGame(1);

      Mockito.doReturn(actualGameFlux).when(gamesRepository).getGameListByTeamId(1, 0, 5);

      final var actualGameList = gamesServiceImpl.getGameListByTeamId(1, 0, 5);
      assertEquals(expectGameList.size(), actualGameList.size());
      IntStream.range(0,2).forEach(i -> assertEquals(expectGameList.get(i), actualGameList.get(i)));
      Mockito.verify(gamesRepository, Mockito.times(1)).getGameListByTeamId(1, 0, 5);
    }
  }

  /**
   * テスト用のGameを作成する
   * @param id 試合IDなどテストデータに使うID(1以上を指定)
   * @return {@link Game}
   */
  private Game createGame (final int id) {
    final var firstTeam = new Team(1, "テストチーム1");
    final var secondTeam = new Team(id + 1, "テストチーム2");
    final var field = new Field(id, "テスト球場");
    final var startDateTime = LocalDateTime.of(2000, 1,1,12,0);
    final var endDateTime = LocalDateTime.of(2000, 1,1,14,0);

    return new Game(id, firstTeam, secondTeam, field, startDateTime, endDateTime);
  }
}
