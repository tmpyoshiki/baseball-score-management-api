package com.baseballscoremanagement.api.application.impl;

import com.baseballscoremanagement.api.domain.model.Game;
import com.baseballscoremanagement.api.domain.repository.GamesRepository;
import com.baseballscoremanagement.api.helper.game.GameCreator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;

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
          .map(GameCreator::createGame);
      final List<Game> expectGameList = IntStream.range(0,2)
          .mapToObj(GameCreator::createGame)
          .collect(Collectors.toList());

      Mockito.doReturn(actualGameFlux).when(gamesRepository).getGameListByTeamId(1, 0, 5);

      final var actualGameList = gamesServiceImpl.getGameListByTeamId(1, 0, 5);
      assertEquals(expectGameList.size(), actualGameList.size());
      IntStream.range(0,2).forEach(i -> assertEquals(expectGameList.get(i), actualGameList.get(i)));
      Mockito.verify(gamesRepository, Mockito.times(1)).getGameListByTeamId(1, 0, 5);
    }
  }
}
