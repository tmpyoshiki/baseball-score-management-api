package com.baseballscoremanagement.api.infrastracture.library;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

  @Test
  void findTeams() {
    final var test = this.teamsMySqlLibrary.findAll().log();
    final var findTeamsFlux = this.teamsMySqlLibrary.findTeams(0,3).log();
    StepVerifier.create(findTeamsFlux).assertNext(teamResponse -> {
      Assertions.assertEquals(1, teamResponse.getId());
      Assertions.assertEquals("テスト", teamResponse.getName());
    }).expectNextCount(0).verifyComplete();
  }
}
