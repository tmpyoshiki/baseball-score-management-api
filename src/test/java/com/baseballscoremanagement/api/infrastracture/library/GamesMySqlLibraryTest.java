package com.baseballscoremanagement.api.infrastracture.library;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataR2dbcTest
class GamesMySqlLibraryTest {

  @Autowired
  private DatabaseClient client;


  @Autowired
  private GamesMySqlLibrary gamesMySqlLibrary;

  @BeforeEach
  public void setup() {
    this.client.sql("CREATE TABLE IF NOT EXISTS FIELDS (" +
        "  `ID` int(4) NOT NULL AUTO_INCREMENT," +
        "  `NAME` varchar(20) DEFAULT NULL," +
        "  PRIMARY KEY (ID)" +
        ")").then().block();
    this.client.sql("CREATE TABLE `SCORES` (" +
        "  `GAME_ID` int(11) NOT NULL," +
        "  `INNING` int(1) NOT NULL," +
        "  `IS_TOP_OF_INNING` tinyint(1) NOT NULL," +
        "  `SCORE` int(2) NOT NULL," +
        "  PRIMARY KEY (`GAME_ID`,`INNING`,`IS_TOP_OF_INNING`)" +
        ")").then().block();
    this.client.sql("CREATE TABLE IF NOT EXISTS TEAMS (" +
        "  ID int(4) NOT NULL AUTO_INCREMENT," +
        "  NAME varchar(15) NOT NULL," +
        "  PRIMARY KEY (ID)" +
        ")").then().block();
    this.client.sql("CREATE TABLE IF NOT EXISTS GAMES (" +
        "   ID int(11) NOT NULL AUTO_INCREMENT," +
        "   BAT_FIRST_TEAM_ID int(4) NOT NULL," +
        "   FIELD_FIRST_TEAM_ID int(4) NOT NULL," +
        "   START_DATE_TIME datetime DEFAULT NULL," +
        "   END_DATE_TIME datetime DEFAULT NULL," +
        "   FIELD_ID int(4) NOT NULL," +
        "  PRIMARY KEY (ID)," +
        "  CONSTRAINT FK_BAT_FIRST_TEAM_ID FOREIGN KEY (BAT_FIRST_TEAM_ID) REFERENCES TEAMS (ID)," +
        "  CONSTRAINT FK_FIELD_FIRST_TEAM_ID FOREIGN KEY (FIELD_FIRST_TEAM_ID) REFERENCES TEAMS (ID)," +
        "  CONSTRAINT FK_FIELD_ID FOREIGN KEY (FIELD_ID) REFERENCES FIELDS (ID)" +
        ")").then().block();
    this.client.sql("INSERT INTO TEAMS VALUES (1, 'テストチーム1');").then().block();
    this.client.sql("INSERT INTO TEAMS VALUES (2, 'テストチーム2');").then().block();
    this.client.sql("INSERT INTO FIELDS VALUES (1, 'テストフィールド');").then().block();
    this.client.sql("INSERT INTO GAMES VALUES (1, 1, 2, '2000-01-01 10:00:00', '2000-01-01 12:00:00', 1);").then().block();
    for (int i = 0; i<9; i++ ) {
      this.client.sql("INSERT INTO SCORES VALUES (1, " + i + 1 + ", TRUE, 2);").then().block();
      this.client.sql("INSERT INTO SCORES VALUES (1, " + i + 1 + ", FALSE, 1);").then().block();
    }
  }

  @AfterEach
  public void tearDown() {
    this.client.sql("DELETE FROM GAMES;").then().block();
    this.client.sql("DELETE FROM TEAMS;").then().block();
    this.client.sql("DELETE FROM FIELDS;").then().block();
    this.client.sql("DELETE FROM SCORES;").then().block();
  }

  @Nested
  class findGamesByTeamId {
    @Test
    void 指定IDの試合が取得できること() {
      final var findGamesFlux = gamesMySqlLibrary.findGamesByTeamId(1,0,3);
      StepVerifier.create(findGamesFlux).assertNext(gameResponse -> {
        assertEquals(1, gameResponse.getId());
        assertEquals(1, gameResponse.getBatFirstTeamId());
        assertEquals("テストチーム1", gameResponse.getBatFirstTeamName());
        assertEquals(18, gameResponse.getBatFirstTeamScore());
        assertEquals(2, gameResponse.getFieldFirstTeamId());
        assertEquals("テストチーム2", gameResponse.getFieldFirstTeamName());
        assertEquals(9, gameResponse.getFieldFirstTeamScore());
        assertEquals(1, gameResponse.getFieldId());
        assertEquals("テストフィールド", gameResponse.getFieldName());
        assertEquals("2000-01-01T10:00", gameResponse.getStartDateTime().toString());
        assertEquals("2000-01-01T12:00", gameResponse.getEndDateTime().toString());
      }).expectNextCount(0).verifyComplete();
    }
  }
}
