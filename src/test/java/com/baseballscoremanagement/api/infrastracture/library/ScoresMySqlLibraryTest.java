package com.baseballscoremanagement.api.infrastracture.library;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataR2dbcTest
class ScoresMySqlLibraryTest {

  @Autowired
  private DatabaseClient client;


  @Autowired
  private ScoresMySqlLibrary scoresMySqlLibrary;

  @BeforeEach
  public void setup() {
    this.client.sql("CREATE TABLE IF NOT EXISTS SCORES (\n" +
        "  GAME_ID INT(11),\n" +
        "  INNING INT(1),\n" +
        "  IS_TOP_OF_INNING BOOLEAN,\n" +
        "  SCORE INT(2) NOT NULL,\n" +
        "  PRIMARY KEY (GAME_ID, INNING, IS_TOP_OF_INNING)\n" +
        ");").then().block();
    for (int i=0; i<9; i++) {
      final int inning = i+1;
      this.client.sql("INSERT INTO SCORES VALUES (1, " + inning + ", TRUE, 1);").then().block();
      this.client.sql("INSERT INTO SCORES VALUES (1, " + inning + ", FALSE, 0);").then().block();
    }
  }

  @AfterEach
  public void tearDown() {
    this.client.sql("DELETE FROM SCORES;").then().block();
  }

  @Nested
  class findGamesByTeamId {
    @Test
    void 指定IDの試合が取得できること() {
      final var findScoresFlux = scoresMySqlLibrary.findTotalScoreByGameId(1).log();
      StepVerifier.create(findScoresFlux).expectNextCount(2).thenConsumeWhile(scoreResponse -> {
        if (scoreResponse.isTopOfInning()) {
          assertEquals(9, scoreResponse.getTotalScore());
        } else {
          assertEquals(0, scoreResponse.getTotalScore());
        }
        return true;
      }).expectNextCount(0).verifyComplete();
    }
  }
}
