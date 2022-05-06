package com.baseballscoremanagement.api.infrastracture.repository;

import com.baseballscoremanagement.api.domain.model.Game;
import com.baseballscoremanagement.api.helper.game.GameCreator;
import com.baseballscoremanagement.api.infrastracture.library.GamesMySqlLibrary;
import com.baseballscoremanagement.api.infrastracture.response.GameResponse;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.baseballscoremanagement.api.helper.game.GameCreator.createGameList;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GamesRepositoryImplTest {
  @Mock
  private GamesMySqlLibrary gamesMySqlLibrary;

  @InjectMocks
  private GamesRepositoryImpl gamesRepository;

  @Nested
  class getGameListByTeamId {
    @Test
    void DBから取得した結果を正しくGameに格納して上位層に返せていること() {
      final List<Game> expectGameList = createGameList(2);
      final Flux<GameResponse> gameResponse
          = Flux.range(0,2)
          .map(GamesRepositoryImplTest.this::createGameResponse);
      Mockito.doReturn(gameResponse).when(gamesMySqlLibrary).findGamesByTeamId(1,0, 2);

      final var actualGameList = gamesRepository.getGameListByTeamId(1, 0, 2);
      StepVerifier
          .create(actualGameList)
          .assertNext(game -> assertEquals(expectGameList.get(0), game))
          .assertNext(game -> assertEquals(expectGameList.get(1), game))
          .expectNextCount(0)
          .verifyComplete();
      Mockito.verify(gamesMySqlLibrary, Mockito.times(1)).findGamesByTeamId(1, 0, 2);
    }
  }

  /**
   * テスト用のGameResponseを作成する
   * @param id 試合IDなどテストデータに使うID(1以上を指定)
   * @return {@link Game}
   */
  private GameResponse createGameResponse (final int id) {
    // gameIdごとにチームID, フィールドIDは変える
    final var startDateTime = LocalDateTime.of(2000, 1,1,12,0);
    final var endDateTime = LocalDateTime.of(2000, 1,1,14,0);

    return new GameResponse(
        id,
        1,
        "テストチーム1",
        id + 1,
        "テストチーム2",
        startDateTime,
        endDateTime,
        id,
        "テスト球場"
    );
  }
}