package com.baseballscoremanagement.api.infrastracture.library;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.test.StepVerifier;


@DataR2dbcTest
class TeamsMySqlLibraryTest {

  @Autowired
  private DatabaseClient client;


  @Autowired
  private TeamsMySqlLibrary teamsMySqlLibrary;

  @BeforeEach
  public void setup() {
    this.client.sql("CREATE TABLE IF NOT EXISTS TEAMS (\n" +
        "  ID int(4) NOT NULL AUTO_INCREMENT,\n" +
        "  NAME varchar(15) NOT NULL,\n" +
        "  PRIMARY KEY (ID)\n" +
        ");").then().block();
    this.client.sql("INSERT INTO TEAMS VALUES (1, 'テスト');").then().block();
  }

  @AfterEach
  public void tearDown() {
    this.client.sql("DELETE FROM TEAMS;").then().block();
  }

  @Nested
  class findTeams () {
    @Test
    void 指定IDの試合が取得できること() {
      final var findTeamsFlux = teamsMySqlLibrary.findTeams(0,1).log();
      StepVerifier.create(findTeamsFlux).assertNext(teamResponse -> {
        Assertions.assertEquals(1, teamResponse.getId());
        Assertions.assertEquals("テスト", teamResponse.getName());
      }).expectNextCount(0).verifyComplete();
    }
  }
}
